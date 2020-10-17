#include <bits/stdc++.h>

using namespace std;

pair<short, short> vertex[10001];
int inf = 2e9;
vector<bool> used(10001, false);
vector<int> minimum_edge(10001, inf);
vector<int> selected(10001, -1);
double ans = 0;

int get_dist(pair<int, int> A, pair<int, int> B) {
    return pow((A.first - B.first), 2) + pow((A.second - B.second), 2);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    short n;
    cin >> n;
    for (short i = 1; i <= n; i++) {
        short x, y;
        cin >> x >> y;
        vertex[i] = {x, y};
    }
    minimum_edge[1] = 0;
    for (int i = 1; i <= n; ++i) {
        int v = -1;
        for (int j = 1; j <= n; ++j)
            if ((!used[j]) && (v == -1 || minimum_edge[j] < minimum_edge[v]))
                v = j;
        used[v] = true;
        if (selected[v] != -1) ans += sqrt(get_dist(vertex[v], vertex[selected[v]]));
        for (int u = 1; u <= n; u++)
            if (get_dist(vertex[v], vertex[u]) < minimum_edge[u] && (selected[u] = v) && (minimum_edge[u] = get_dist(vertex[v], vertex[u])));
    }
    cout << ans;
    return 0;
}