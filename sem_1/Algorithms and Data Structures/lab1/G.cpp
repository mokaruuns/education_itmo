#include <bits/stdc++.h>
using  namespace std;

#define ll long long

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    ll n,x,y;
    cin >> n >> x >> y;
    ll cnt = min(x,y);
    n--;
    ll left = 0, right = n;
    ll m1,m2;
    ll f1, f2;
    for (int i = 0;  i < 100; i++) {
        m1 = (left+right)/2;
        m2 = (left+right)/2+1;
        f1 = max(x*m1,y*(n-m1));
        f2 = max(x*m2,y*(n-m2));
        if (f1<f2) {
            right = m2;
        }
        else {
            left = m1;
        }
    }
    cnt += min(f1,f2);
    cout << cnt;
    return 0;
} 
