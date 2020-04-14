#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>
#include <string.h>

struct data_encoding {
    unsigned char *data;
    int size;
};

struct data_utf_to_unicode {
    unsigned int *data;
    int size;
};

bool add(unsigned int *a, int *size, int *position, unsigned int value) {
    if (*position + 1 >= *size) {
        unsigned int *tmp = realloc(a, sizeof(int) * (*size) * 2 + 1);
        if (!tmp) {
            perror("realloc() ");
            free(a);
            free(tmp);
            return false;
        } else {
            a = tmp;
        }
        *size = *size * 2 + 1;
    }
    a[(*position)++] = value;
    return true;
}

unsigned char *read_bytes(const char *file_name, int *position) {
    FILE *fin;
    int size = 0;
    if ((fin = fopen(file_name, "rb")) == NULL) {
        perror("fopen() ");
        return NULL;
    }
    unsigned char b;
    unsigned char *buf = malloc(0);
    while (fread(&b, 1, 1, fin)) {
        buf[*position] = b;
        (*position)++;
        if (*position + 1 >= size) {
            unsigned char *tmp = realloc(buf, sizeof(int) * size * 2 + 1);
            if (!tmp) {
                perror("realloc() ");
                fclose(fin);
                free(buf);
                free(tmp);
                return NULL;
            } else {
                buf = tmp;
            }
            size = size * 2 + 1;
        }
    }
    fclose(fin);
    return buf;
}


bool equal_arr(const unsigned char *data, const unsigned char *encode_arr, int encode_arr_size) {
    for (int i = 0; i < encode_arr_size; i++) {
        if (data[i] != encode_arr[i]) {
            return false;
        }
    }
    return true;
}

bool byte_is_wrong(unsigned char byte) {
    return (byte >> 6u) != 2;
}

struct data_utf_to_unicode utf8_to_unicode(const unsigned char *utf_encoding, int size, bool bom) {
    unsigned int *unicode_encoding = malloc(0);
    int i = 3 * bom;
    int data_size = 0;
    int j = 0;
    while (i < size) {
        unsigned int res = 0;
        unsigned int c = utf_encoding[i++];
        if (!(c >> 7u)) {
            res = c;
            add(unicode_encoding, &data_size, &j, res);
        } else {
            if ((c & 224u) == 192) {
                unsigned int b2 = c;
                unsigned int b1 = utf_encoding[i++];
                if (byte_is_wrong(b1)) {
                    unsigned int res2 = (b2 & 127u) + 0xDC80;
                    unsigned int res1 = (b1 & 127u) + 0xDC80;
                    add(unicode_encoding, &data_size, &j, res2);
                    add(unicode_encoding, &data_size, &j, res1);
                } else {
                    b2 = (b2 & 31u) << 6u;
                    b1 = b1 & 63u;
                    res = b1 + b2;
                    add(unicode_encoding, &data_size, &j, res);
                }
            } else if ((c & 240u) == 224) {
                unsigned int b3 = c;
                unsigned int b2 = utf_encoding[i++];
                unsigned int b1 = utf_encoding[i++];
                if (byte_is_wrong(b1) || byte_is_wrong(b2)) {
                    unsigned int res3 = (b3 & 127u) + 0xDC80;
                    unsigned int res2 = (b2 & 127u) + 0xDC80;
                    unsigned int res1 = (b1 & 127u) + 0xDC80;
                    add(unicode_encoding, &data_size, &j, res3);
                    add(unicode_encoding, &data_size, &j, res2);
                    add(unicode_encoding, &data_size, &j, res1);
                } else {
                    b3 = (b3 & 15u) << 12u;
                    b2 = (b2 & 63u) << 6u;
                    b1 = b1 & 63u;
                    res = b1 + b2 + b3;
                    add(unicode_encoding, &data_size, &j, res);
                }
            } else if ((c & 248u) == 240) {
                unsigned int b4 = c;
                unsigned int b3 = utf_encoding[i++];
                unsigned int b2 = utf_encoding[i++];
                unsigned int b1 = utf_encoding[i++];
                if (byte_is_wrong(b1) || byte_is_wrong(b2) || byte_is_wrong(b3)) {
                    unsigned int res4 = (b4 & 127u) + 0xDC80;
                    unsigned int res3 = (b3 & 127u) + 0xDC80;
                    unsigned int res2 = (b2 & 127u) + 0xDC80;
                    unsigned int res1 = (b1 & 127u) + 0xDC80;
                    add(unicode_encoding, &data_size, &j, res4);
                    add(unicode_encoding, &data_size, &j, res3);
                    add(unicode_encoding, &data_size, &j, res2);
                    add(unicode_encoding, &data_size, &j, res1);
                } else {
                    b4 = (b4 & 7u) << 18u;
                    b3 = (b3 & 63u) << 12u;
                    b2 = (b2 & 63u) << 6u;
                    b1 = (b1 & 63u);
                    res = b1 + b2 + b3 + b4;
                    add(unicode_encoding, &data_size, &j, res);
                }
            } else {
                res = (c & 127u) + 0xDC80;
                add(unicode_encoding, &data_size, &j, res);
            }
        }
    }
    struct data_utf_to_unicode tmp = {unicode_encoding, j};
    return tmp;
}

struct data_encoding unicode_to_utf8_bom(const unsigned int *data, int size) {
    unsigned char *output_encoding = malloc(sizeof(unsigned char) * 4 * (size + 1));
    int position = 0;
    output_encoding[position++] = 0xEF;
    output_encoding[position++] = 0xBB;
    output_encoding[position++] = 0xBF;
    int i = 0;
    while (i < size) {
        unsigned char res = 0;
        if (0xDC80 <= data[i] && data[i] <= 0xDCFF) {
            unsigned char b = (data[i] - 0xDC80) + 128;
            output_encoding[position++] = b;
        } else {
            if (data[i] < (1u << 7u)) {
                res = data[i];
                output_encoding[position++] = res;
            } else if (data[i] < 1u << 11u) {
                unsigned char b1 = (data[i] & 63u) + 128;
                unsigned char b2 = ((data[i] >> 6u) & 31u) + 192;
                output_encoding[position++] = b2;
                output_encoding[position++] = b1;
            } else if (data[i] < 1u << 16u) {
                unsigned char b1 = (data[i] & 63u) + 128;
                unsigned char b2 = ((data[i] >> 6u) & 63u) + 128;
                unsigned char b3 = ((data[i] >> 12u) & 15u) + 224;
                output_encoding[position++] = b3;
                output_encoding[position++] = b2;
                output_encoding[position++] = b1;
            } else if (data[i] < 1u << 21u) {
                unsigned char b1 = (data[i] & 63u) + 128;
                unsigned char b2 = ((data[i] >> 6u) & 63u) + 128;
                unsigned char b3 = ((data[i] >> 12u) & 63u) + 128;
                unsigned char b4 = ((data[i] >> 18u) & 7u) + 240;
                output_encoding[position++] = b4;
                output_encoding[position++] = b3;
                output_encoding[position++] = b2;
                output_encoding[position++] = b1;
            }
        }
        i++;
    }
    struct data_encoding tmp = {output_encoding, position};
    return tmp;
}

struct data_utf_to_unicode utf16_to_unicode(const unsigned char *utf_encoding, int size, bool is_be) {
    unsigned int *unicode_encoding = malloc(0);
    int i = 2;
    int j = 0;
    int data_size = 0;
    while (i < size) {
        unsigned int c = utf_encoding[i++];
        unsigned int p = utf_encoding[i++];
        unsigned int tmp = 0;
        if (is_be) {
            tmp = (c << 8u) + p;
        } else {
            tmp = (p << 8u) + c;
        }
        unsigned int res = tmp;
        if (tmp >= 55296) {
            c = utf_encoding[i++];
            p = utf_encoding[i++];
            unsigned int tmp2 = 0;
            if (is_be) {
                tmp2 = (c << 8u) + p;
            } else {
                tmp2 = (p << 8u) + c;
            }
            unsigned int b1 = ((tmp - 55296) << 10u) + 65536;
            unsigned int b2 = tmp2 & ((1u << 10u) - 1u);
            res = b1 + b2;
        }
        add(unicode_encoding, &data_size, &j, res);
    }
    struct data_utf_to_unicode tmp = {unicode_encoding, j};
    return tmp;
}

void put_byte(unsigned char *output_encoding, int *position, unsigned int res, bool is_be) {
    if (is_be) {
        output_encoding[(*position)++] = res >> 8u;
        output_encoding[(*position)++] = res & ((1u << 8u) - 1);
    } else {
        output_encoding[(*position)++] = res & ((1u << 8u) - 1);
        output_encoding[(*position)++] = res >> 8u;
    }
}

struct data_encoding unicode_to_utf16(const unsigned int *data, int size, bool is_be) {
    unsigned char *output_encoding = malloc(sizeof(unsigned char) * 4 * (size + 1));
    int i = 0;
    int position = 0;
    put_byte(output_encoding, &position, 0xFEFF, is_be);
    while (i < size) {
        unsigned int res = 0;
        if ((data[i] < (1u << 16u) && (data[i] < 55296 || data[i] > 57343))) {
            res = data[i];
            put_byte(output_encoding, &position, res, is_be);

        } else {
            unsigned int b1 = (data[i] >> 10u) - 64 + 55296;
            unsigned int b2 = (data[i] & ((1u << 10u) - 1)) + 56320;
            put_byte(output_encoding, &position, b1, is_be);
            put_byte(output_encoding, &position, b2, is_be);

        }
        i++;
    }
    struct data_encoding tmp = {output_encoding, position};
    return tmp;
}

struct data_utf_to_unicode utf32_to_unicode(const unsigned char *utf_encoding, int size, bool is_be) {
    unsigned int *unicode_encoding = malloc(0);
    int i = 4;
    int j = 0;
    int data_size = 0;
    while (i < size) {
        unsigned int a = utf_encoding[i++];
        unsigned int b = utf_encoding[i++];
        unsigned int c = utf_encoding[i++];
        unsigned int d = utf_encoding[i++];
        if (is_be) {
            add(unicode_encoding, &data_size, &j, ((a << 24u) + (b << 16u) + (c << 8u) + d));
        } else {
            add(unicode_encoding, &data_size, &j, (d << 24u) + (c << 16u) + (b << 8u) + a);
        }
    }
    struct data_utf_to_unicode tmp = {unicode_encoding, j};
    return tmp;
}

struct data_encoding unicode_to_utf32(const unsigned int *data, int size, bool is_be) {
    unsigned char *output_encoding = malloc(sizeof(unsigned char) * 4 * (size + 1));
    int i = 0;
    int position = 0;
    if (is_be) {
        output_encoding[position++] = 0x00;
        output_encoding[position++] = 0x00;
        output_encoding[position++] = 0xFE;
        output_encoding[position++] = 0xFF;
    } else {
        output_encoding[position++] = 0xFF;
        output_encoding[position++] = 0xFE;
        output_encoding[position++] = 0x00;
        output_encoding[position++] = 0x00;
    }
    while (i < size) {
        unsigned int x = data[i++];
        unsigned int a = (x >> 24u) & 255u;
        unsigned int b = (x >> 16u) & 255u;
        unsigned int c = (x >> 8u) & 255u;
        unsigned int d = x & 255u;
        if (is_be) {
            output_encoding[position++] = a;
            output_encoding[position++] = b;
            output_encoding[position++] = c;
            output_encoding[position++] = d;
        } else {
            output_encoding[position++] = d;
            output_encoding[position++] = c;
            output_encoding[position++] = b;
            output_encoding[position++] = a;
        }
    }
    struct data_encoding tmp = {output_encoding, position};
    return tmp;
}

struct data_encoding read_encoding(const char *fin) {
    int position = 0;
    unsigned char *data = read_bytes(fin, &position);
    struct data_encoding tmp = {data, position};
    return tmp;
}


struct data_utf_to_unicode utf_to_unicode(unsigned char *data, int size) {
    const unsigned char utf8_bom[3] = {0xEF, 0xBB, 0xBF};
    const unsigned char utf16_bom_be[2] = {0xFE, 0xFF};
    const unsigned char utf16_bom_le[2] = {0xFF, 0xFE};
    const unsigned char utf32_bom_be[4] = {0x00, 0x00, 0xFE, 0xFF};
    const unsigned char utf32_bom_le[4] = {0xFF, 0xFE, 0x00, 0x00};
    if (equal_arr(data, utf32_bom_be, 4))return utf32_to_unicode(data, size, true); // "utf32_bom_be";
    if (equal_arr(data, utf32_bom_le, 4))return utf32_to_unicode(data, size, false); //"utf32_bom_le";
    if (equal_arr(data, utf16_bom_be, 2))return utf16_to_unicode(data, size, true); // "utf16_bom_be";
    if (equal_arr(data, utf16_bom_le, 2)) return utf16_to_unicode(data, size, false); // "utf16_bom_le"
    if (equal_arr(data, utf8_bom, 3))return utf8_to_unicode(data, size, true); // "utf8_bom";
    return utf8_to_unicode(data, size, false); // "utf8";
}

struct data_encoding unicode_to_utf(const char *output_encoding, unsigned int *unicode_encoding, int size) {
    if (strcmp(output_encoding, "utf8") == 0) {
        return unicode_to_utf8_bom(unicode_encoding, size);
    }
    if (strcmp(output_encoding, "utf16_be") == 0) {
        return unicode_to_utf16(unicode_encoding, size, true);
    }
    if (strcmp(output_encoding, "utf16_le") == 0) {
        return unicode_to_utf16(unicode_encoding, size, false);
    }
    if (strcmp(output_encoding, "utf32_be") == 0) {
        return unicode_to_utf32(unicode_encoding, size, true);
    }
    if (strcmp(output_encoding, "utf32_le") == 0) {
        return unicode_to_utf32(unicode_encoding, size, false);
    }
}

bool write_output_encoding(const char *file_name, unsigned char *output_data, int size) {
    FILE *fout = NULL;
    if ((fout = fopen(file_name, "wb")) == NULL) {
        perror("fopen() ");
        return false;
    }
    fwrite(output_data, sizeof(unsigned char), size, fout);
    fclose(fout);
    return true;
}


void get_wrong_byte(const char *file_name) { // запись в файл неправильных байтов для проверки
    FILE *fout = NULL;
    if ((fout = fopen(file_name, "wb")) == NULL) {
        perror("fopen() ");
    }
    unsigned char output_data[8] = {0xF0, 0x24, 0x24, 0x24, 0xC1, 0x24, 0x20, 0xAF};
    fwrite(output_data, sizeof(unsigned char), 8, fout);
    fclose(fout);
}

int main(int argc, char **argv) {
    const char *fin, *fout, *output_encoding; // output_encoding: utf8, utf16_be, utf16_le, utf32_be, utf32_le
    fin = argv[1];
    fout = argv[2];
    output_encoding = argv[3];
    // считывание кодировки
    struct data_encoding first_data = read_encoding(fin);
    // перекодировка в unicode
    struct data_utf_to_unicode second_data = utf_to_unicode(first_data.data, first_data.size);
    unsigned int *unicode_data = second_data.data;
    int unicode_data_size = second_data.size;
    // перекодировка в utf
    struct data_encoding third_data = unicode_to_utf(output_encoding, unicode_data, unicode_data_size);
    // вывод кодировки
    write_output_encoding(fout, third_data.data, third_data.size);
    free(first_data.data);
    free(second_data.data);
    free(third_data.data);
//    get_wrong_byte(fin);
}