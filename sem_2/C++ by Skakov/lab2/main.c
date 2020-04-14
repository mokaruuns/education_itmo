#include <stdio.h>
#include <string.h>
#include <malloc.h>
#define MAX_STACK 2048

struct info {
    char name[21], surname[21], patronymic[21];
    unsigned long long phone;
};

int comp(const struct info first, const struct info second, const char *order) {
    int a = 0;
    for (int i = 0; i < 4; i++) {
        switch (order[i]) {
            case 'n':
                a = strcmp(first.name, second.name);
                break;
            case 's':
                a = strcmp(first.surname, second.surname);
                break;
            case 'p':
                a = strcmp(first.patronymic, second.patronymic);
                break;
            case 'h' :
                if (first.phone < second.phone) {
                    a = -1;
                } else if (first.phone > second.phone) {
                    a = 1;
                } else {
                    a = 0;
                }
                break;
        }
        if (a < 0) { return -1; }
        else if (a > 0) { return 1; }
    }
    return 0;
}

void qsort_it(struct info *a, int size, char *order) {
    int stack_left[MAX_STACK], stack_right[MAX_STACK];
    int position = 0;
    stack_left[0] = 0;
    stack_right[0] = size - 1;
    while (position >= 0) {
        int left = stack_left[position];
        int right = stack_right[position--];
        while (left < right) {
            int pivot_position = (left + right) >> 1;
            int i = left;
            int j = right;
            struct info pivot = a[pivot_position];
            while (i <= j) {
                while (comp(a[i], pivot, order) < 0) i++;
                while (comp(pivot, a[j], order) < 0) j--;
                if (i <= j) {
                    struct info temp = a[i];
                    a[i++] = a[j];
                    a[j--] = temp;
                }
            }
            if (i < pivot_position) {
                if (i < right) {
                    stack_left[++position] = i;
                    stack_right[position] = right;
                }
                right = j;
            } else {
                if (j > left) {
                    stack_left[++position] = left;
                    stack_right[position] = j;
                }
                left = i;
            }
        }
    }
}

int main(int argc, char **argv) {
    char *order = "nsph"; // n-name, s-surname, p-patronymic, h-phone. Место в строке определяет приоритет поля
    const char *fin, *fout;
    fin = argv[1];
    fout = argv[2];
    FILE *fp;
    if ((fp = fopen(fin, "r")) == NULL) {
        perror("fopen() ");
        return 1;
    }
    struct info *data = malloc(sizeof(struct info));
    if (!data) {
        perror("malloc() ");
        fclose(fp);
        return 2;
    }
    int size = 1;
    int position = 0;
    while (fscanf(fp, "%s%s%s%llu", data[position].name, data[position].surname, data[position].patronymic,
                  &data[position].phone) != EOF) {
        position++;
        if (position + 1 >= size) {
            struct info *tmp = realloc(data, sizeof(struct info) * size * 2 + 1);
            if (!tmp) {
                perror("realloc() ");
                fclose(fp);
                free(data);
                return 2;
            } else {
                data = tmp;
            }
            size = size * 2 + 1;
        }
    }
    fclose(fp);
    qsort_it(data, position, order);
    if ((fp = fopen(fout, "w")) == NULL) {
        perror("fopen() ");
        free(data);
        return 1;
    }
    for (int j = 0; j < position; j++) {
        fprintf(fp, "%s %s %s %llu\n", data[j].name, data[j].surname, data[j].patronymic, data[j].phone);
    }
    free(data);
    fclose(fp);
    return 0;
}
