#include <bits/stdc++.h>

using namespace std;
#define ll long long

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    vector<ll> c;
    vector<ll> dp;
    vector<ll> idvi;
    ll n, k;
    cin >> n >> k;
    c.push_back(0);
    dp.push_back(0);
    for (int i = 0; i < n - 2; i++) {
        int tmp;
        cin >> tmp;
        c.push_back(tmp);
        dp.push_back(0);
    }
    c.push_back(0);
    dp.push_back(0);
    for (int i = 1; i < n; i++) {
        ll my_max = -1000000000;
        ll id = 0;
        for (int j = 1; j < k + 1; j++) {
            if (i - j >= 0) {
                if (dp[i - j] > my_max) {
                    my_max = dp[i - j];
                    id = i - j + 1;
                }
            }
        }
        dp[i] = my_max + c[i];
        idvi.push_back(id);
    }
    vector<ll> lili;
    lili.push_back(n);
    ll i = idvi.size() - 1;
    while (i > 0) {
        lili.push_back(idvi[i]);
        i = idvi[i] - 2;
    }
    if (lili[lili.size()-1]!=1){
        lili.push_back(1);
    }
    reverse(lili.begin(), lili.end());
    cout << dp[dp.size() - 1] << endl << lili.size() - 1 << endl;
    for (int i : lili) {
        cout << i << " ";
    }

    return 0;
} 
