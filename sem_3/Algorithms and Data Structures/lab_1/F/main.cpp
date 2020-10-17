#include <bits/stdc++.h>

using namespace std;

bool visited[20001];
bool visited_tree[20001];
vector<set<int>> g(20001);
vector<set<int>> gr(20001);
vector<int> order;
vector<int> component(20001);
map<int, map<int, int>> multiple_edges;

void dfs_one(int v) {
    if (visited[v]) return;
    visited[v] = true;
    for (int u: g[v]) dfs_one(u);
    order.push_back(v);
}

int cur_component = 0;

void dfs_two(int v, int id) {
    if (component[v] != 0) return;
    component[v] = id;
    for (int u: gr[v]) {
        dfs_two(u, id);
    }
}
int edges = 0;

void dfs_edge(int v){
    if (visited_tree[v]) return;
    visited_tree[v] = true;
    for (int u: g[v]) {
        multiple_edges[component[v]][component[u]] = (component[v]!=component[u]);
        dfs_one(u);
    }
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
        g[v].insert(u);
        gr[u].insert(v);
    }
    for (int i = 1; i <= n; i++) {
        if (!visited[i]) {
            dfs_one(i);
        }
    }
    reverse(order.begin(), order.end());
    for (auto i: order) {
        if (component[i] == 0) {
            cur_component++;
            dfs_two(i, cur_component);
        }
    }
    for (int i = 1; i <= n; i++) {
        if (!visited_tree[i]) {
            dfs_edge(i);
        }
    }
    for (const auto& x: multiple_edges){
        for(auto y: x.second){
            edges+=y.second;
        }
    }
    cout << edges;

    return 0;
}