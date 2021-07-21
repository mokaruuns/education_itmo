#include <bits/stdc++.h>

#define endl "\n"
#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("test.in", "r", stdin);
    freopen("test.out", "w", stdout);
}


vector<string> s(110);
vector<vector<int> > g;
vector<int> info, used, matching;

int dfs(int v) {
    if (used[v]) return 0;
    used[v] = 1;
    FOR(i, 0, g[v].size()) {
        int to = g[v][i];
        if (info[to] == -1 || dfs(info[to])) {
            info[to] = v;
            matching[v] = to;
            return 1;
        }
    }
    return 0;
}

void added_path(int n, int m) {
    matching.assign(n * m, -1);
    info.assign(n * m, -1);
    int flag = 1;
    while (flag){
        flag = 0;
        used.assign(n * m, 0);
        FOR(i, 0, n)
            FOR(j, 0, m) {
                if ((i + j) % 2) continue;
                if ((matching[i * m + j] == -1) && dfs(i * m + j)) flag = 1;
            }
    }
}

void solve() {
    int n, m, a, b, empty = 0;
    cin >> n >> m >> a >> b;
    FOR(i, 0, n) cin >> s[i];
    g.resize(n * m);
    FOR(i, 0, n)
        FOR(j, 0, m) {
            if (s[i][j] == '.') continue;
            empty++;
            if ((i + j) % 2) continue;
            int u = i * m + j;
            if (j && (s[i][j - 1] == '*')) g[u].push_back(u - 1);
            if ((j < m - 1) && (s[i][j + 1] == '*')) g[u].push_back(u + 1);
            if (i && (s[i - 1][j] == '*')) g[u].push_back(u - m);
            if ((i < n - 1) && (s[i + 1][j] == '*')) g[u].push_back(u + m);
        }
    if (2 * b <= a) {
        cout << empty * b << endl;
        return;
    }
    added_path(n, m);
    int c = 0;
    FOR(i, 0, n * m) if (info[i] != -1) c++;
    int result = c * a + (empty - 2 * c) * b;
    cout << result << endl;
}

int main() {
    fastIO();
    solve();
    return 0;

}