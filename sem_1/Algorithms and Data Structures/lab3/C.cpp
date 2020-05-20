#include "bits/stdc++.h"

using namespace std;

int main() {
//    freopen("input.txt", "r", stdin);
//    freopen("output.txt", "w", stdout);
    int n;
    cin >> n;
    vector<int> dp(n, 1);
    vector<int> vi(n);
    vector<int> pi;
    vector<int> bi(n, -99);
    for (int i = 0; i < n; i++) {
        cin >> vi[i];
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (vi[i] > vi[j] && dp[i] <= dp[j]) {
                dp[i] = dp[j] + 1;
                bi[i] = j;
            }
        }
    }
    int m = dp[0];
    int j = 0;
    for (int i = 0; i < n; i++) {
        if (dp[i] > m) {
            m = dp[i];
            j = i;
        }
    }
    cout << m << endl;
    while (j != -99) {
        pi.push_back(vi[j]);
        j = bi[j];
    }
    reverse(pi.begin(), pi.end());
    for (int e: pi) {
        cout << e << " ";
    }
    return 0;
} 
