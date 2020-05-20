#include <bits/stdc++.h>

using namespace std;
int n;
int d[200010];
int dp[200010][20];
int dm[200010][20];

void binary_ups() {
    cin >> n;
    for (int i = 0; i < n - 1; i++) {
        cin >> dp[i + 2][0] >> dm[i + 2][0];
        d[i + 2] = d[dp[i + 2][0]] + 1;
    }
    for (int j = 1; j < 20; j++) {
        for (int i = 1; i < n + 1; i++) {
            dp[i][j] = dp[dp[i][j - 1]][j - 1];
            dm[i][j] = min(dm[i][j - 1], dm[dp[i][j - 1]][j - 1]);
        }
    }
}

int get_lca(int v, int u) {
    if (d[v] < d[u]) swap(v, u);
    while (d[v] != d[u]) v = dp[v][(int) log2(d[v] - d[u])];
    if (v == u) return v;
    else {
        for (int i = 19; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return dp[v][0];
    }
}

int get_min_path(int v, int u) {
    int x = get_lca(v, u);
    int m_v = 10e9;
    int m_u = 10e9;
    while (d[v] != d[x]) {
        int l = (int) log2(d[v] - d[x]);
        m_v = min(m_v, dm[v][l]);
        v = dp[v][l];
    }
    while (d[u] != d[x]) {
        int l = (int) log2(d[u] - d[x]);
        m_u = min(m_u, dm[u][l]);
        u = dp[u][l];
    }
    return min(m_v, m_u);

}

void requests() {
    int m = 0;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int v, u;
        cin >> v >> u;
        cout << get_min_path(v, u) << "\n";
    }
}

int main() {
    freopen("minonpath.in", "r", stdin);
    freopen("minonpath.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    binary_ups();
    requests();
    return 0;
}
