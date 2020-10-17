#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> g(20001);
map<int, map<int, int>> mii;

int visited[20001];
int time_in[20001];
int time_out[20001];
vector<int> colors(20001);
stack<int> st;
int now_color = 0;
int t = 0;

void paint(int v) {
    now_color++;
    int last = -1;
    while (last != v && !st.empty()) {
        colors[st.top()] = now_color;
        last = st.top();
        st.pop();
    }
}

void dfs(int v, int hint) {
    st.push(v);
    visited[v] = true;
    time_in[v] = t++;
    time_out[v] = t;
    for (int u: g[v]) {
        if (u == hint) continue;
        if (visited[u]) time_out[v] = min(time_out[v], time_in[u]);
        else {
            dfs(u, v);
            time_out[v] = min(time_out[v], time_out[u]);
            if (time_out[u] > time_in[v]) {
                if (mii[v][u] == 1){
                    paint(u);
                }
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= m; i++) {
        int v, u;
        cin >> v >> u;
        g[v].push_back(u);
        g[u].push_back(v);
        mii[v][u]++;
        mii[u][v]++;
    }
    for (int i = 1; i <= n; i++)
        if (!visited[i]) {
            dfs(i, -1);
            paint(i);
        }
    cout << now_color << endl;
    for (int i = 1; i <= n; i++) cout << colors[i] << " ";
    return 0;
}