#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("schedule.in", "r", stdin);
    freopen("schedule.out", "w", stdout);
}

vector<pair<int, int>> vpii;

bool comp(pair<int, int> a, pair<int, int> b) {
    if (a.first < b.first) {
        return true;
    }
    if (a.first == b.first) {
        if (a.second > b.second) { return true; }
    }
    return false;
}

bool comp2(pair<int, int> a, pair<int, int> b) {
    return a.second > b.second;
}

set<int> ver_reverse;

void solve() {
    int n;
    cin >> n;
    FOR(i, 0, n) {
        int d, w;
        cin >> d >> w;
        vpii.emplace_back(1e6 - d , w);
//        cout << 1e6 - d << endl;
    }
    sort(vpii.begin(), vpii.end(), comp2);
    FOR(i, 0, 1e6+1) {
        ver_reverse.insert(i);
    }
    ll ans = 0;
    for (pair<int, int> i: vpii) {
//        cout << *ver_reverse.upper_bound( i.first) << endl;
        if (i.first < 0) {
            continue;
        } else {
            if (ver_reverse.upper_bound( i.first) != ver_reverse.end()) {
                ver_reverse.erase(*ver_reverse.upper_bound(i.first));
            } else {
                ans += i.second;
            }
        }
    }
    cout << ans;
}

int main() {
    fastIO();
    solve();
    return 0;
}