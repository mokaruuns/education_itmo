#include <bits/stdc++.h>

using namespace std;
int cnts = 1e9 + 7;
int act[100001];
vector<map<int, int>> vmii(100001);
vector<map<int, int>> vmii_reverse(100001);
vector<int> sorted_vector;
int color[100001];
bool useful[100001];

void dfs_useful(int v) {
    useful[v] = true;
    for (auto j: vmii_reverse[v]) {
        if (!useful[j.first]) {
            dfs_useful(j.first);
        }
    }
}

bool dfs(int start) {
    bool is_cycle = false;
    color[start] = 1;
    for (auto j : vmii[start]) {
        if (color[j.first] == 0) {
            is_cycle = dfs(j.first);
        }
        if (useful[j.first] && color[j.first] == 1) {
            return true;
        }
    }
    sorted_vector.push_back(start);
    color[start] = 2;
    return is_cycle;
}

int sum[100001];

int main() {
    freopen("problem3.in", "r", stdin);
    freopen("problem3.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m, k;
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++) {
        int tmp;
        cin >> tmp;
        act[tmp] = 1;
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        vmii[a][b]++;
        vmii_reverse[b][a]++;
    }
    for(int i = 0; i <= n; i++) {
        if (act[i]) {
            dfs_useful(i);
        }
    }
    if (dfs(1)) {
        cout << -1;
    } else {
        sum[1] = 1;
        reverse(sorted_vector.begin(), sorted_vector.end());
        for (auto i: sorted_vector) {
            for (auto j: vmii_reverse[i]) {
                sum[i] = (sum[i] + sum[j.first] * j.second) % cnts;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (act[i]) {
                ans = (ans + sum[i]) % cnts;
            }
        }
        cout << ans;
    }

}