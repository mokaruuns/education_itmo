#include <bits/stdc++.h>

typedef long long ll;
using namespace std;

int a[10000000];
int c[20000000];
ll sum[10000000];

int main() {
    ll n, x, y, a0;
    cin >> n >> x >> y >> a0;
    a[0] = a0;
    ll m, z, t, b0;
    cin >> m >> z >> t >> b0;c[0] = b0 % n;
    for (int i = 1; i < n; i++) {
        a[i] = (x * a[i - 1] + y) % (1 << 16);
    }
    ll b = b0;
    for (int i = 1; i < 2 * m; i++) {
        b = (z * b + t) % (1 << 30);
        c[i] = b % n;
    }
    sum[0] = a[0];
    for (int i = 1; i < n; i++) {
        sum[i] = (sum[i - 1] + a[i]);
    }
    ll answer = 0;
    for (int i = 0; i < m; i++) {
        ll left = min(c[2 * i], c[2 * i + 1]);
        ll right = max(c[2 * i], c[2 * i + 1]);
        if (left < 0) left = 0;
        answer += sum[right] - sum[left - 1];
    }
    cout << answer;
    return 0;
} 
