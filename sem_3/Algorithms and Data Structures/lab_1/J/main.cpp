#include <bits/stdc++.h>

using namespace std;

struct Edge {
    long long from;
    long long to;
    long long weight;
};

vector<Edge> g;
long long parent[200001];

long long get_root(long long v) {
    if (parent[v] < 0) return v;
    else {
        parent[v] = get_root(parent[v]);
        return parent[v];
    }
}

bool join(long long left, long long right) {
    left = get_root(left);
    right = get_root(right);
    if (left == right) return false;
    if (left > right) swap(left, right);
    parent[left] += parent[right];
    parent[right] = left;
    return true;
}

bool comp(Edge left, Edge right) {
    return left.weight < right.weight;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    memset(parent, -1, sizeof(parent));
    long long n, m;
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int v, u, w;
        cin >> v >> u >> w;
        g.push_back({v, u, w});
    }
    sort(g.begin(), g.end(), comp);
    long long ans = 0;
    for (auto e: g) if (join(e.from, e.to)) ans += e.weight;
    cout << ans;
    return 0;
}