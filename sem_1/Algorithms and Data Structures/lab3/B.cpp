#include <bits/stdc++.h>

using namespace std;
#define ll long long
#define endl "\n"

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n, m;
    cin >> n >> m;
    vector<vector<int>> vvii(n);
    for (int i = 0; i < n; i++) {
        vector<int> vi(m);
        for (int j = 0; j < m; j++) {
            cin >> vi[j];
        }
        vvii[i] = vi;
    }
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    vector<vector<char>> dp_str(n + 1, vector<char>(m + 1, 'e'));
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (i-1==0){
                dp[i][j] = dp[i][j-1] + vvii[i - 1][j - 1];
                dp_str[i][j] = 'R';
                continue;
            }
            if (j-1==0){
                dp[i][j] = dp[i-1][j] + vvii[i - 1][j - 1];
                dp_str[i][j] = 'D';
                continue;
            }
            if (dp[i - 1][j] > dp[i][j - 1]) {
                dp[i][j] = dp[i - 1][j] + vvii[i - 1][j - 1];
                dp_str[i][j] = 'D';
            } else {
                dp[i][j] = dp[i][j - 1] + vvii[i - 1][j - 1];
                dp_str[i][j] = 'R';
            }
        }
    }
    cout << dp[n][m] << endl;
    int i = n, j = m;
    vector<char> vch;
    while (i > 1 or j > 1) {
        vch.push_back(dp_str[i][j]);
        if (dp_str[i][j] == 'D') {
            i--;
        } else {
            j--;
        }
    }
    reverse(vch.begin(), vch.end());
    for (char ch: vch) {
        cout << ch;
    }
    return 0;
} 
