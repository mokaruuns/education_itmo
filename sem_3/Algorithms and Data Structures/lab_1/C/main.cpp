#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> g(20001);
int visited[20001];
int time_in[20001];
int time_out[20001];
set<int> ans;

int t = 0;

void dfs(int v, int hint) {
    visited[v] = true;
    time_in[v] = t++;
    time_out[v] = t;
    int ch = 0;
    for (int u: g[v]) {
        if (u == hint) continue;
        if (visited[u]) time_out[v] = min(time_out[v], time_in[u]);
        else {
            dfs(u, v);
            time_out[v] = min(time_out[v], time_out[u]);
            if (time_out[u] >= time_in[v] && hint != -1) ans.insert(v);
            ch++;
        }
    }
    if (hint==-1 && ch > 1) ans.insert(v);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= m; i++) {
        int v, u;
        cin >> v >> u;
        g[v].push_back(u);
        g[u].push_back(v);
    }
    for (int i = 1; i <= n; i++) if (!visited[i]) dfs(i, -1);
    cout << ans.size() << endl;
    for (auto i : ans) cout << i << " ";
    return 0;
}