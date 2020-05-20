#include "bits/stdc++.h"

using namespace std;

void read_dka(vector<map<char, int>> *dka, vector<char> *terminated) {
    int n, m, k;
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++) {
        int tmp;
        cin >> tmp;
        terminated->at(tmp) = 1;
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        dka->at(a)[c] = b;
    }
}

vector<map<char, int>> dka_first(100001);
vector<map<char, int>> dka_second(100001);
vector<char> terminated_first(100001);
vector<char> terminated_second(100001);
char visited[100001];
map<int, int> as;

bool is_isomorphic(int start_first, int start_second) {
    visited[start_first] = 1;
    as[start_first] = start_second;
    if (terminated_first[start_first] != terminated_second[start_second]) {
        return false;
    }
    bool res = true;
    for (auto x: dka_first[start_first]) {
        int a = dka_first[start_first][x.first];
        int b = dka_second[start_second][x.first];
        if ((a == start_first) ^ (b == start_second)) {
            return false;
        }
        res &= (visited[a] ? (b == as[a]) : is_isomorphic(a, b));
    }
    return res;

}

char visited_eq[1001][1001];

bool is_equivalent(int start_first, int start_second) {
    queue<pair<int, int>> que;
    que.push(make_pair(start_first, start_second));
    while (!que.empty()) {
        int a = que.front().first;
        int b = que.front().second;
        que.pop();
        visited_eq[a][b] = 1;
        if (terminated_first[a] != terminated_second[b]) {
            return false;
        }

        for (char c = 'a'; c < 'z'; c++) {
            int w1 = dka_first[a][c];
            int w2 = dka_second[b][c];
            if (!visited_eq[w1][w2]) {
                que.push(make_pair(w1, w2));
            }
        }
    }
    return true;
}

int main() {
    freopen("equivalence.in", "r", stdin);
    freopen("equivalence.out", "w", stdout);
    read_dka(&dka_first, &terminated_first);
    read_dka(&dka_second, &terminated_second);
    cout << (is_equivalent(1, 1) ? "YES" : "NO");
    return 0;
}