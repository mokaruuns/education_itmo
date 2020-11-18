#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void read(int &n, int &m, map<int, map<int, int>> &matrix) {
    cin >> n >> m;
    FOR(i, 0, m) {
        int begin, end, weight;
        cin >> begin >> end >> weight;
        matrix[begin - 1][end - 1] = weight;
        matrix[end - 1][begin - 1] = weight;
    }
}

void write(const vector<int> &shortest) {
    for (int weight: shortest) {
        cout << weight << " ";
    }
}

vector<int> dijkstra(int &n, map<int, map<int, int>> &matrix) {
    vector<int> shortest(n, 1e9);
    set<pair<int, int> > a;
    a.insert({0, 0});
    while (!a.empty()) {
        int weight = (*a.begin()).first;
        int begin = (*a.begin()).second;
        a.erase(a.begin());
        for (auto current_vertex: matrix[begin]) {
            int end = current_vertex.first, current_weight = current_vertex.second;
            if (weight + current_weight < shortest[end]) {
                shortest[end] = weight + current_weight;
                a.insert({shortest[end], end});
            }
        }
    }
    shortest[0] = 0;
    return shortest;
}

void solve() {
    int m, n;
    map<int, map<int, int>> matrix;
    read(n, m, matrix);
    write(dijkstra(n, matrix));
}

int main() {
    fastIO();
    solve();
    return 0;
}
