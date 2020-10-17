#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> g(20001);
int visited[20001];
int in_time[20001];
int out_time[20001];
vector<pair<int, int>> edges;
stack<pair<int, int>> st_edges;
map<pair<int, int>, int> colors;
set<int> c;
int t = 0;
int color = 0;
int max_color = 0;

void paint(int v, int hint) {
    visited[v] = true;
    in_time[v] = out_time[v] = t++;
    for (auto u: g[v]) {
        if (u == hint) continue;
        if (!visited[u]) {
            st_edges.push(make_pair(v, u));
            paint(u, v);
            if (out_time[u] >= in_time[v]) {
                color = max_color++;
                while (st_edges.top() != make_pair(v, u)) {
                    colors[st_edges.top()] = color;
                    c.insert(color);
                    st_edges.pop();
                }
                colors[{v, u}] = color;
                c.insert(color);
                st_edges.pop();
            }
            if (out_time[u] < out_time[v]){
                out_time[u] = out_time[v];
            }
        } else if (in_time[u] < in_time[v]){
            st_edges.push({v,u});
            if (in_time[u] < out_time[v]) {
                out_time[v] = in_time[u];
            }
        } else if (out_time[v] > in_time[u]) {
            out_time[v] = out_time[u];
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
        edges.emplace_back(v, u);
    }
//    for (int i = 1; i <= n; i++) if (!visited[i]) dfs(i, -1);
//    memset(visited, false, sizeof(visited));
    for (int v = 1; v <= n; v++){
        if (!visited[v]){
            t = 0;
            max_color++;
            paint(v, -1);
        }
    }
    cout << c.size() << endl;
    for (auto vu: edges) {
        cout << colors[vu] << " ";
    }
    return 0;
}