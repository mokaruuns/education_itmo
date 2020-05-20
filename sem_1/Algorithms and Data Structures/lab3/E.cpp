#include <bits/stdc++.h>

using namespace std;
int d[1001][1001];

int main() {
    string a, b;
    cin >> a >> b;
    int n = a.size();
    int m = b.size();
    for (int i = 0; i < n; i++) {
        d[i][0] = i;
    }
    for (int i = 0; i < m; i++) {
        d[0][i] = i;
    }
    for (int i = 1; i < n+1; i++) {
        for (int j = 1; j < m+1; j++) {
            if (a[i - 1] == b[j - 1]) {
                d[i][j] += d[i - 1][j - 1];
            } else {
                d[i][j] += (min(min(d[i - 1][j - 1], d[i][j - 1]), d[i - 1][j]) + 1);
            }
        }
    }
    cout << d[n][m];
    return 0;
} 
