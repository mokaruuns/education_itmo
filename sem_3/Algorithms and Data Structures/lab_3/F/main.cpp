#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef int ll;


using namespace std;

const int P = 59;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

ll get_hash(const vector<ll> &h, ll begin, ll end) {
    return h[end] - (begin > 0 ? h[begin - 1] : 0);
}


void solve() {
    vector<ll> answer;
    string small, big;
    cin >> small >> big;
    if (big.size() < small.size()) {
        cout << 0;
        return;
    }
    vector<ll> big_hash;
    ll small_hash = 0;
    vector<ll> fast_pow;
    fast_pow.emplace_back(1);
    FOR(i, 1, big.size()) {
        fast_pow.emplace_back(fast_pow.back() * P);
    }
    big_hash.emplace_back((big[0] - 'a' + 1));
    FOR(i, 1, big.size()) {
        big_hash.emplace_back((big[i] - 'a' + 1) * fast_pow[i] + big_hash.back());
    }
    FOR(i, 0, small.size()) {
        small_hash += (small[i] - 'a' + 1) * fast_pow[i];
    }
    FOR(i, 0, big.size() - small.size() + 1) {
        if (small_hash * fast_pow[i] == get_hash(big_hash, i, i + small.size() - 1)) {
            answer.emplace_back(i + 1);
        }
//        small_hash = (P * small_hash - fast_pow[])
    }
    cout << answer.size() << endl;
    for (ll x: answer) {
        cout << x << " ";
    }

}

int main() {
    fastIO();
    solve();
    return 0;
}