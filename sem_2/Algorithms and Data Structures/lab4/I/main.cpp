#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
    char *in = "sqroot.in";
    char *out = "sqroot.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}
int get_m(int num, int id) { return (num >> (15 - id)) & 1; }

vector<vector<int>> num_to_matrix(int num) {
    vector<vector<int>> matrix2(4, vector<int>(4));
    forn(16) {
        int x = i / 4;
        int y = i % 4;
        matrix2[x][y] = get_m(num, i);
        cout << matrix2[x][y] << " ";
        cout << x << " " << y << endl;
    }
    return matrix2;
}

vector<vector<int>> mul(vector<vector<int>> A, vector<vector<int>> B) {
    vector<vector<int>> C(4, vector<int>(4));
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            C[i * 4][j] = 0;
            for (int k = 0; k < 4; ++k)
                C[i * 4][j] += A[i * 4][k] * B[k * 4][j];
        }
    }
    return C;
}
int main() {
    open_file_rw();
    vector<vector<int>> matrix(4, vector<int>(4));
    fori(0, 4) {
        forj(0, 4) {
            int tmp;
            cin >> tmp;
            matrix[i][j] = tmp;
        }
    }

    vector<vector<int>> matrix_res =
        mul(num_to_matrix(1), num_to_matrix(1));
    fori(0, 4) {
        forj(0, 4) { cout << matrix_res[i][j] << " "; }
        cout << "\n";
    }
}