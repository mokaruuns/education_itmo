#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef unsigned long long ll;


using namespace std;

const int P = 31;
vector<ll> h;
vector<ll> ps;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

ll get_hash(int begin, int end) {
    return h[end - 1] - (begin - 1 != 0 ? h[begin - 2] : 0);
}

void hash_string(string str) {
    ps.emplace_back(1);
    FOR(i, 0, str.size()) {
        ps.emplace_back(ps.back() * P);
        h.emplace_back((str[i] - 'a' + 1) * ps.back());
        if (i) h[i] += h[i - 1];
    }
}

void solve() {
    string str;
    int m;
    int a, b, c, d;
    cin >> str;
    cin >> m;
    hash_string(str);
    FOR(i, 0, m) {
        cin >> a >> b >> c >> d;
        cout << ((((a <= c) && (get_hash(a, b) * ps[c - a] == get_hash(c, d))) ||
                  ((a >= c) && (get_hash(a, b) == get_hash(c, d) * ps[a - c]))) ? "Yes" : "No") << endl;
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}
