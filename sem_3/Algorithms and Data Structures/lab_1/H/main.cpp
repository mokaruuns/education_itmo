#include <bits/stdc++.h>

using namespace std;

int g[1001][1001];
int g_is_ok[1001][1001];
set<int> sorted_cost;
int n;
bool used[1001];


void dfs(int v) {
    used[v] = true;
    for (int u = 1; u <= n; u++)
        if (g_is_ok[v][u] && !used[u]) dfs(u);
}

void dfs_back(int v) {
    used[v] = true;
    for (int u = 1; u <= n; u++)
        if (g_is_ok[u][v] && !used[u]) dfs_back(u);
}


int is_visited() {
    for (int v = 1; v <= n; v++)
        if (!used[v]) return 0;
    return 1;
}


int main() {
    freopen("avia.in", "r", stdin);
    freopen("avia.out", "w", stdout);
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n;
    for (int v = 1; v <= n; v++) {
        for (int u = 1; u <= n; u++) {
            cin >> g[v][u];
            sorted_cost.insert(g[v][u]);
        }
    }
    int l = 0;
    int r = 1e9;
    while (l < r) {
        int m = (l + r) / 2;
        for (int v = 1; v <= n; v++)
            for (int u = 1; u <= n; u++)
                g_is_ok[v][u] = (g[v][u] <= m);
        memset(used, 0, sizeof(used));
        dfs(1);
        if (is_visited()) {
            memset(used, 0, sizeof(used));
            dfs_back(1);
            if (!is_visited()) {
                l = m + 1;
                continue;
            }
        } else {
            l = m + 1;
            continue;
        }
        r = m;
    }
    cout << l;
    return 0;
}
