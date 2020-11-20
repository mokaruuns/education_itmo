#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (uint variable = begin; variable < end; variable++)
typedef long long ll;
typedef unsigned int uint;


using namespace std;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void read(uint &n, uint &m, uint &start, map<uint, map<uint, uint>> &matrix) {
    cin >> n >> m >> start;
    FOR(i, 0, m) {
        uint begin, end, weight;
        cin >> begin >> end >> weight;
        matrix[begin][end] = weight;
        matrix[end][begin] = weight;
    }
}

void write(const vector<uint> &shortest) {
    for (uint weight: shortest) {
        cout << weight << " ";
    }
}

vector<uint> dijkstra(uint &n, map<uint, map<uint, uint>> &matrix, uint &start) {
    vector<uint> shortest(n, 2009000999);
    set<pair<uint, uint>> a;
    a.insert({0, start});
    while (!a.empty()) {
        uint weight = (*a.begin()).first;
        uint begin = (*a.begin()).second;
        a.erase(a.begin());
        for (auto current_vertex: matrix[begin]) {
            uint end = current_vertex.first, current_weight = current_vertex.second;
            if (weight + current_weight < shortest[end]) {
                shortest[end] = weight + current_weight;
                a.insert({shortest[end], end});
            }
        }
    }
    shortest[start] = 0;
    return shortest;
}

void solve() {
    uint m, n, start;
    map<uint, map<uint, uint>> matrix;
    read(n, m, start,matrix);
    write(dijkstra(n, matrix, start));
}

int main() {
    fastIO();
    solve();
    return 0;
}