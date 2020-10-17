#include <bits/stdc++.h>

using namespace std;

bool visited[100001];
vector<vector<int>> g(100001);
vector<int> ans;
int color[100001];

bool is_cycle(int start) {
    bool isCycle = false;
    color[start] = 1;
    for (auto j : g[start]) {
        if (color[j] == 0) {
            isCycle = is_cycle(j);
        }
        if (color[j] == 1) {
            return true;
        }
    }
    color[start] = 2;
    return isCycle;
}

void dfs(int v) {
    visited[v] = true;
    for (int u: g[v]) {
        if (!visited[u]) {
            dfs(u);
        }
    }
    ans.push_back(v);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int v, u;
        cin >> v >> u;
        g[v].push_back(u);
    }
    for (int i = 1; i <= n; i++) {
        if (is_cycle(i)) {
            cout << -1;
            return 0;
        }
        if (!visited[i]) {
            dfs(i);
        }
    }
    reverse(ans.begin(), ans.end());
    for (auto i: ans) {
        cout << i << " ";
    }
    return 0;
}
