#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> g(20001);
int visited[20001];
int visited2[20001];
int time_in[20001];
int time_out[20001];
stack<pair<int, int>> st;
map<int, map<int, int>> colors;
set<int> ans;
int now_color = 0;
int t = 0;
vector<pair<int, int>> vpii;

void dfs(int v, int hint) {
    visited[v] = true;
    time_in[v] = t;
    time_out[v] = t;
    t++;
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
    if (hint == -1 && ch > 1) ans.insert(v);
}

void dfs_paint(int v, int color, int hint) {
    visited2[v] = true;
    for (int u: g[v]) {
        if (u == hint) continue;
        if (visited2[u]) {
            if (time_in[u] <= time_in[v]) {
                colors[v][u] = color;
                colors[u][v] = color;
            }
        } else {
            if (time_out[u] >= time_in[v]) {
                int n_color = ++now_color;
                colors[v][u] = n_color;
                colors[u][v] = n_color;
                dfs_paint(u, n_color, v);
            } else {
                colors[v][u] = color;
                colors[u][v] = color;
                dfs_paint(u, color, v);
            }
        }
    }
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
        vpii.emplace_back(v, u);
//        vpii.emplace_back(u, v);
    }
    for (int i = 1; i <= n; i++)
        if (!visited[i]) {
            dfs(i, -1);
        }
    for (int i = 1; i <= n; i++)
        if (!visited2[i]) {
            dfs_paint(i, now_color, -1);
        }
    cout << now_color << endl;
    for (auto my_pair : vpii) {
        cout << colors[my_pair.first][my_pair.second] << " ";
    }
    return 0;
}