#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;

struct Vertex {
    int number;
    int weight;
};

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("matching.in", "r", stdin);
    freopen("matching.out", "w", stdout);
}

bool comp(Vertex first, Vertex second) {
    return first.weight >= second.weight;
}

vector<Vertex> vertex;
vector<vector<int>> vvi(1001);
vector<int> m_left(1001, -1);
vector<int> m_right(1001, -1);
vector<bool> used(1001, false);


bool kuun(int v) {
    if (used[v]) return false;
    used[v] = true;
    for (auto to: vvi[v]) {
        if (m_left[to] == -1 || kuun(m_left[to])) {
            m_left[to] = v;
            m_right[v] = to;
            return true;
        }
    }
    return false;
}

void solve() {
    int n;
    cin >> n;
    FOR(i, 1, n + 1) {
        int w;
        cin >> w;
        vertex.push_back({i, w * w});
    }
    FOR(i, 1, n + 1) {
        int m;
        cin >> m;
        FOR(j, 1, m + 1) {
            int k;
            cin >> k;
            vvi[i].emplace_back(k);
        }
    }
    sort(vertex.begin(), vertex.end(), comp);
    for (Vertex v: vertex) {
        used.assign(1001, false);
        kuun(v.number);
    }
    FOR(i, 1, n + 1) {
        cout << (m_right[i] == -1 ? 0 : m_right[i]) << " ";
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}