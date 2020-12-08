#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;
map<int, set<int>> edges;
map<int, int> vertex_size;
set<int> leafs;
bool used[100001];

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void solve() {
    int n;
    cin >> n;
    vector<int> answer(n - 2);
    FOR(i, 0, n - 1) {
        int v, u;
        cin >> v >> u;
        edges[v].insert(u);
        edges[u].insert(v);
        vertex_size[v]++;
        vertex_size[u]++;
    }
    for (pair<int, int> vertex: vertex_size) {
        if (vertex.second == 1) {
            leafs.insert(vertex.first);
        }
        used[vertex.first] = true;
    }
    FOR(i, 0, n - 2) {
        int leaf = *leafs.begin();
        int v;
        leafs.erase(leafs.begin());
        used[leaf] = false;
        for (auto u: edges[leaf]) {
            if (used[u]) {
                v = u;
            }
        }
        answer[i] = v;
        vertex_size[v]--;
        if (vertex_size[v] == 1) {
            leafs.insert(v);
        }
    }
    for (auto i: answer) {
        cout << i << " ";
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}
