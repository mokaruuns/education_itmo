#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("rainbow.in", "r", stdin);
    freopen("rainbow.out", "w", stdout);
}

int n, m;
map<int, map<int, int>> graph;
vector<int> parent(n + 1);
set<int> s;

void solve() {
    cin >> n >> m;
    FOR(i, 0, m) {
        int from, to, color;
        cin >> from >> to >> color;
        graph[from][to] = color;
        graph[to][from] = color;
    }
    FOR(i, 1, n + 1) {
        parent[i] = i;
    }
    s.insert(1);
}


int main() {
    fastIO();
    solve();
    return 0;
}