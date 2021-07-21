#include <bits/stdc++.h>

#define endl "\n"
#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
//    freopen("test.in", "r", stdin);
//    freopen("test.out", "w", stdout);
}

vector<vector<int>> g;
vector<int> px, py;
vector<bool> used;

bool dfs(int x) {
    if (used[x]) return false;
    used[x] = true;
    for (int y: g[x]) {
        if (py[y] == -1) {
            py[y] = x;
            px[x] = y;
            return true;
        } else {
            if (dfs(py[y])) {
                py[y] = x;
                px[x] = y;
                return true;
            }
        }
    }
    return false;
}


void solve() {
    int n, m;
    cin >> n >> m;
    px.assign(251, -1);
    py.assign(251, -1);
    g.emplace_back();
    FOR(i, 1, n + 1) {
        int x;
        cin >> x;
        g.emplace_back();
        while (x != 0) {
            g[i].push_back(x);
            cin >> x;
        }
    }
    bool isPath = true;
    while (isPath) {
        isPath = false;
        used.assign(251, false);
        for (int x = 1; x <= n; x++) {
            if (px[x] == -1) {
                if (dfs(x)) isPath = true;
            }
        }
    }
    int count = 0;
    for (int i = 1; i <= n; i++) {
        count += px[i] != -1;
    }
    cout << count << endl;
    for (int i = 1; i <= n; i++) {
        if (px[i] != -1) {
            cout << i << " " << px[i] << endl;
        }
    }
}


int main() {
    fastIO();
    solve();
    return 0;
}