#include <bits/stdc++.h>

#define ENDL "\n";
typedef long long LL;
using namespace std;

vector<vector<int>> vi(100001, vector<int>(50, INT_MAX));

int main() {
    int m, n, a1;
    int u1, v1;
    cin >> n >> m >> a1;
    cin >> u1 >> v1;
    vi[0][0] = a1;
    for (int i = 1; i < n; i++) {
        vi[i][0] = ((23 * vi[i - 1][0] + 21563) % 16714589);
        //cout << vi[i][0] << " ";
    }

    for (int k = 1; k < (int) log2(n) + 1; k++) {
        for (int i = 0; i <= n - (1 << k); i++) {
            vi[i][k] = min(vi[i][k - 1], vi[i + (1 << (k - 1))][k - 1]);
        }
    }
    int new_u = u1, new_v = v1;
    int l, r;
    int res;
    for (int i = 1; i <= m; i++) {
        int left = min(new_u, new_v) - 1;
        int right = max(new_u, new_v) - 1;
        int k = (int) log2(right - left + 1);
        res = min(vi[left][k], vi[right - (1 << k) + 1][k]);
        //cout << new_u << " " << new_v << " " << res;
        l = new_u;
        r = new_v;
        new_u = (17 * new_u + 751 + res + 2 * i) % n + 1;
        new_v = (13 * new_v + 593 + res + 5 * i) % n + 1;
    }
    cout << l << " " << r << " " << res;
    return 0;
}
 
