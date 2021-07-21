#include <bits/stdc++.h>

#define endl "\n"
#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;

ll const INF = INT_MAX;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
//    freopen("test.in", "r", stdin);
//    freopen("test.out", "w", stdout);
}


struct edge {
    int from;
    int to;
    int flow;
    int capacity;
};

int n, m;
int s, t;

vector<edge> edges;
vector<vector<int>> graph;
vector<int> d, p;
vector<vector<char>> table;
vector<int> score;
vector<int> max_score;

void addEdges(int from, int to, int c) {
    graph[from].push_back((int) edges.size());
    edges.push_back({from, to, 0, c});
    graph[to].push_back((int) edges.size());
    edges.push_back({to, from, 0, 0});
}

bool bfs() {
    queue<int> q;
    q.push(s);
    d.assign(d.size(), -1);
    d[s] = 0;
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (auto e : graph[u]) {
            struct edge edg = edges[e];
            if (edg.flow < edg.capacity && d[edg.to] == -1) {
                d[edg.to] = d[u] + 1;
                q.push(edg.to);
            }
        }
    }
    return d[t] != -1;
}

int dfs(int u, int min_c) {
    if (u == t || min_c == 0) {
        return min_c;
    }
    while (p[u] < graph[u].size()) {
        int e = graph[u][p[u]];
        struct edge edg = edges[e];
        if (d[edg.to] != d[u] + 1) {
            p[u]++;
            continue;
        }
        int pushed = dfs(edg.to, min(min_c, edg.capacity - edg.flow));
        if (pushed) {
            edges[e].flow += pushed;
            edges[e ^ 1].flow -= pushed;
            return pushed;
        }
        p[u]++;
    }
    return 0;
}

int findFlow() {
    int ans = 0;
    while (bfs()) {
        p.assign(p.size(), 0);
        while (int cur_flow = dfs(s, INF)) ans += cur_flow;
    }
    return ans;
}

void readTable() {
    FOR(i, 0, n) {
        FOR(j, 0, n) {
            char c;
            cin >> c;
            table[i][j] = c;
            if (c == 'W') {
                score[i] += 3;
            } else if (c == 'l') {
                score[i] += 1;
            } else if (c == 'w') {
                score[i] += 2;
            } else if (c == '.') {
                if (i < j) {
                    addEdges(i, j, 3);
                    max_score[i] += 3;
                }
            }
        }
    }
    FOR(i, 0, n) {
        int k;
        cin >> k;
        addEdges(s, i, max_score[i]);
        addEdges(i, t, k - score[i]);
    }
}

void fillTable() {
    FOR(i, 0, n) {
        for (auto e : graph[i]) {
            struct edge edg = edges[e];
            if (table[i][edg.to] == '.' && i < edg.to && edg.to != t && edg.to != s)
                switch (edg.flow) {
                    case 0 : {
                        table[i][edg.to] = 'W';
                        table[edg.to][i] = 'L';
                        break;
                    }
                    case 1 : {
                        table[i][edg.to] = 'w';
                        table[edg.to][i] = 'l';
                        break;
                    }
                    case 2 : {
                        table[i][edg.to] = 'l';
                        table[edg.to][i] = 'w';
                        break;
                    }
                    case 3 : {
                        table[i][edg.to] = 'L';
                        table[edg.to][i] = 'W';
                        break;
                    }
                    default: {
                        break;
                    }
                }
        }
    }

}

void initialize() {
    graph.resize(n + 2);
    d.resize(n + 2);
    p.resize(n + 2);
    table.resize(n + 2, vector<char>(n + 2));
    score.resize(n, 0);
    max_score.resize(n, 0);
}

void solve() {
    cin >> n;
    initialize();
    s = n;
    t = n + 1;
    readTable();
    int max_flow = findFlow();
    fillTable();
    FOR(i, 0, n) {
        FOR(j, 0, n) {
            cout << table[i][j];
        }
        cout << endl;
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}