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
        // cout << matrix2[x][y] << " ";
        // cout << x << " " << y << endl;
    }
    return matrix2;
}

vector<vector<int>> mul(vector<vector<int>> A, vector<vector<int>> B) {
    vector<vector<int>> C(4, vector<int>(4));
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            C[i][j] = 0;
            for (int k = 0; k < 4; ++k)
                C[i][j] = (C[i][j] + (A[i][k] * B[k][j]) % 2) % 2;
        }
    }
    return C;
}

bool check(vector<vector<int>> A, vector<vector<int>> B) {
    fori(0, 4) {
        forj(0, 4) {
            if (A[i][j] != B[i][j]) {
                return false;
            }
        }
    }
    return true;
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

    for (int k = 0; k < (1 << 16); k++) {
        vector<vector<int>> matrix_sqrt = num_to_matrix(k);
        vector<vector<int>> matrix_res = mul(matrix_sqrt, matrix_sqrt);
        if (check(matrix_res, matrix)) {
            fori(0, 4) {
                forj(0, 4) { cout << matrix_sqrt[i][j] << " "; }
                cout << "\n";
            }
            return 0;
        }
    }
    cout << "NO SOLUTION";
}