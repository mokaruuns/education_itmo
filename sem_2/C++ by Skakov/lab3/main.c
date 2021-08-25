#include <stdio.h>
#include <malloc.h>
#include <math.h>

void clean_mem(float *matrix[], size_t height, float *ans) {
    free(ans);
    for (int i = 0; i < height; i++) {
        free(matrix[i]);
    }
    free(matrix);
}

int main(int argc, char **argv) {
    const char *fin, *fout;
    fin = argv[1];
    fout = argv[2];
    FILE *fp;
    // open input file
    if ((fp = fopen(fin, "r")) == NULL) {
        perror("fopen() ");
        return 1;
    }
    // reading matrix
    size_t n, width, height;
    fscanf(fp, "%d", &n);
    width = n + 1;
    height = n;
    float **matrix = malloc(width * sizeof(float *));
    float *ans = malloc(sizeof(float) * height);
    if (!ans || !matrix) {
        perror("malloc() ");
        clean_mem(matrix, height, ans);
        fclose(fp);
        return 2;
    }
    for (int i = 0; i < height; i++) {
        matrix[i] = malloc(sizeof(float) * width);
        if (!matrix[i]) {
            perror("malloc() ");
            clean_mem(matrix, height, ans);
            fclose(fp);
            return 2;
        }
        for (int j = 0; j < width; j++) {
            fscanf(fp, "%f", &matrix[i][j]);
        }
    }
    fclose(fp);
    // прямой ход
    if ((fp = fopen(fout, "w")) == NULL) {
        perror("fopen() ");
        clean_mem(matrix, height, ans);
        return 1;
    }
    float eps = 1e-7f;
    for (size_t i = 0; i < height; i++) {
        size_t j_max = i;
        for (size_t j = i; j < height; j++)
            j_max = (fabsf(matrix[j][i]) > fabsf(matrix[j_max][i])) ? j : j_max;
        if (fabsf(matrix[j_max][i]) < eps) {
            if (fabsf(matrix[j_max][height]) < eps) {
                fprintf(fp, "many solutions");
            } else {
                fprintf(fp, "no solutions");
            }
            clean_mem(matrix, height, ans);
            fclose(fp);
            return 0;
        }
        for (size_t x = 0; x < width; x++) {
            float tmp = matrix[i][x];
            matrix[i][x] = matrix[j_max][x];
            matrix[j_max][x] = tmp;
        }
        for (size_t j = i + 1; j < height; j++) {
            double t1 = matrix[j][i];
            double t2 = matrix[i][i];
            if (i == j) continue;
            for (size_t k = i; k < width; k++) {
                matrix[j][k] -= matrix[i][k] * t1 / t2;
            }
        }
    }
    // обратный ход
    for (size_t i = height - 1; i < height; i--) {
        ans[i] = matrix[i][width - 1];
        for (size_t j = i + 1; j < width - 1; j++) {
            ans[i] -= ans[j] * matrix[i][j];
        }
        ans[i] /= matrix[i][i];
    }

    for (int i = 0; i < height; i++) {
        fprintf(fp, "%0.6f\n", ans[i]);
    }
    clean_mem(matrix, height, ans);
    fclose(fp);
    return 0;
}
