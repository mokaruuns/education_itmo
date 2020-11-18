#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void read(int &size, vector<vector<int>> &matrix) {
    cin >> size;
    FOR(i, 0, size) {
        matrix.emplace_back();
        FOR(j, 0, size) {
            int tmp;
            cin >> tmp;
            matrix.back().push_back(tmp);
        }
    }
}

void write(const int& size, const vector<vector<int>> &matrix){
    FOR(u, 0, size) {
        FOR(v, 0, size) {
            cout << matrix[u][v] << " ";
        }
        cout << "\n";
    }
}

void floyd(int size, vector<vector<int>> &matrix) {
    FOR(i, 0, size) {
        FOR(u, 0, size) {
            FOR(v, 0, size) {
                matrix[u][v] = min(matrix[u][v], matrix[u][i] + matrix[i][v]);
            }
        }
    }
}

void solve() {
    int size;
    vector<vector<int>> matrix;
    read(size, matrix);
    floyd(size, matrix);
    write(size, matrix);
}

int main() {
    fastIO();
    solve();
    return 0;
}
