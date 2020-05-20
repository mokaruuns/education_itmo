#include <bits/stdc++.h>
using  namespace std;

#define ll long long

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    ll w,h,n;
    cin >> w >> h >> n;
    ll left = 0;
    ll right = max(w,h)*n;
    ll m;
    for (int i = 0; i < 100; i++) {
        m = (left+right)/2;
        if (ceil(m/w)*ceil(m/h)>=n) {
            right = m;
        }
        else {
            left = m;
        }
    }
    cout << right;
    return 0;
} 
