#include "bits/stdc++.h"
#define ll long long
using namespace std;

int main() {
//    freopen("input.txt", "r", stdin);
//    freopen("output.txt", "w", stdout);
    ll n;
    ll inf = 1e9;
    cin >> n;
    vector<vector<ll>> numbers(10);
    numbers[0] = {4, 6};
    numbers[1] = {6, 8};
    numbers[2] = {7, 9};
    numbers[3] = {4, 8};
    numbers[4] = {0, 3, 9};
    numbers[5] = {};
    numbers[6] = {0, 1, 7};
    numbers[7] = {2, 6};
    numbers[8] = {1, 3};
    numbers[9] = {2, 4};
    vector<vector<ll>> dp(10, vector<ll>(n + 1, 0));
    for (int i = 0; i < 10; i++) {
            dp[i][1] = 1;
    }
    dp[8][1] = 0;
    dp[0][1] = 0;
    for (ll i = 2; i < n + 1; i++) { // количество цифр
        for (ll j = 0; j < 10; j++) { //цифра с которой начинается
            ll s = 0;
            for (ll k = 0; k < numbers[j].size(); k++) {
                s = (s + dp[numbers[j][k]][i - 1]) % inf;
            }
            dp[j][i] = s;
        }
    }
    ll ans = 0;
    for (int i = 0; i < 10; i++) {
        ans = (ans + dp[i][n]) % inf;
    }
    cout << ans;
    return 0;
} 
