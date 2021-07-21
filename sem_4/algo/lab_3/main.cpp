#include <bits/stdc++.h>

#define endl "\n"
#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;

ll const INF = INT_MAX;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
//    freopen("test.in", "r", stdin);
//    freopen("test.out", "w", stdout);
}


ll gcd(ll a, ll b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}

//ll mul(ll a, ll b, ll m) {
//    if (b == 1)
//        return a;
//    if (b % 2 == 0) {
//        ll t = mul(a, b / 2, m);
//        return (2 * t) % m;
//    }
//    return (mul(a, b - 1, m) + a) % m;
//}

ll mul(ll a, ll b, ll mod) {
    if (!a || !b) return 0;
    if (a & 1) return (b + mul(a - 1, b, mod)) % mod;
    return (mul(a / 2, b, mod) * 2) % mod;
}

ll binpow (ll a, ll n, ll mod) {
    ll res = 1;
    while (n) {
        if (n & 1)
            res = (res * a) % mod;
        a = (a * a) % mod;
        n = (n >> 1) % mod;
    }
    return res % mod;
}

long long pows(long long a, long long b, long long m) {
    if (b == 0)
        return 1;
    if (b % 2 == 0) {
        long long t = pows(a, b / 2, m);
        return mul(t, t, m) % m;
    }
    return (mul(pows(a, b - 1, m), a, m)) % m;
}

bool isPrime(ll k, ll x) {
    if (x == 2)
        return true;
    if (x == 1 || x % 2 == 0) {
        return false;
    }
    for (int i = 0; i < k; i++) {
        ll a = (rand() % (x - 2)) + 2;
        if (gcd(a, x) != 1)
            return false;
        if (binpow(a, x - 1, x) != 1)
            return false;
    }
    return true;
}

void solve() {
    int n;
    cin >> n;
    FOR(i, 0, n) {
        ll number;
        cin >> number;
        cout << (isPrime(10, number) ? "YES" : "NO") << endl;
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}