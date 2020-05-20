#include "bits/stdc++.h"

using namespace std;

int cnts = 1e9 + 7;
int act[101];
vector<map<char, int>> vmii(101);
int cnt[101][1001];

int main() {
    freopen("problem4.in", "r", stdin);
    freopen("problem4.out", "w", stdout);
    int n, m, k, l;
    cin >> n >> m >> k >> l;
    for (int i = 0; i < k; i++) {
        int tmp;
        cin >> tmp;
        act[tmp] = 1;
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        vmii[a][c] = b;
    }
    cnt[1][0] = 1;
    for (int j = 0; j < l; j++) {
        for (int i = 1; i <= n; i++) {
            for (auto x : vmii[i]) {
                cnt[x.second][j + 1] = (cnt[x.second][j + 1] + cnt[i][j]) % cnts;
            }
        }
    }
    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum = (sum + act[i] * cnt[i][l]) % cnts;
    }
    cout << sum;
    return 0;
}