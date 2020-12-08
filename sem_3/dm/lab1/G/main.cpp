#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

map<int, vector<int>> graph;
vector<int> colors;
map<int, int> vertex_size;

bool comp(pair<int, int> a, pair<int, int> b) {
    return a.second >= b.second;
}


void solve() {
    colors.push_back(1);
    int n, m;
    cin >> n >> m;
    FOR(i, 0, m) {
        int v, u;
        cin >> v >> u;
        graph[v].emplace_back(u);
        graph[u].emplace_back(v);
        vertex_size[v]++;
        vertex_size[u]++;
    }
    sort(vertex_size.begin(), vertex_size.end(), comp);

}

int main() {
    fastIO();
    solve();
    return 0;
}
