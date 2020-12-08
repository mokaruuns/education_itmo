#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;
map<int, set<int>> edges;
vector<int> vertex_size(100001, 1);
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
    vector<int> answer(n - 1);
    FOR(i, 1, n - 1) cin >> answer[i];
    for (int v: answer) vertex_size[v]++;
    FOR(i, 1, n + 1) {
        if (vertex_size[i] == 1) leafs.insert(i);
    }
    FOR(i, 1, n - 1) {
        int leaf = *leafs.begin();
        leafs.erase(leafs.begin());
        int v = answer[i];
        edges[leaf].insert(v);
        vertex_size[v]--;
        if (vertex_size[v] == 1) {
            leafs.insert(v);
        }
    }
    edges[*leafs.begin()].insert(*(--leafs.end()));
    for (const auto &edge: edges) {
        for (auto u: edge.second) {
            cout << edge.first << " " << u << endl;
        }
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}
