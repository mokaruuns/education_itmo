#include <bits/stdc++.h>

using namespace std;
int dp[111111][200];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    for (int i = 1; i <= n; i++) {
        int tmp;
        cin >> tmp;
        dp[i][0] = tmp;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j < ceil(log2(n)); j++) {
            dp[i][j] = dp[dp[i][j - 1]][j - 1];
        }
    }
    for (int i = 1; i <= n; i++) {
        cout << i << ": ";
        for (int j = 0; j < ceil(log2(n)); j++) {
            if (dp[i][j]) cout << dp[i][j] << " ";
        }
        cout << "\n";
    }
    return 0;
}
