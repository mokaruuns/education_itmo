#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("check.in", "r", stdin);
    freopen("check.out", "w", stdout);
}

int MAX_SIZE = 1 << 10 + 1;
int cnt;
int n, m;
vector<vector<int>> s;
vector<bool> useful(MAX_SIZE, false);
vector<int> mask_size(MAX_SIZE, 0);

bool has_empty = false;

bool check_first() {
    return has_empty;
}

bool check_second() {
    FOR(i, 0, cnt) {
        if (useful[i]) {
            FOR(j, 0, cnt) {
                if ((i | j) == i && !useful[j]) {
                    return false;
                }
            }
        }
    }
    return true;
}

bool check_third() {
    FOR(i, 0, cnt) {
        if (useful[i]) {
            FOR(j, 0, cnt) {
                bool hasA = false;
                if (useful[j] && mask_size[i] > mask_size[j]) {
                    FOR(k, 0, n) {
                        int a = 1 << k;
                        if ((i & a) && !(j & a) && useful[j | a]) {
                            hasA = true;
                        }
                    }
                    if (!hasA) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}

bool main_check() {
    return check_first() && check_second() && check_third();
}

void solve() {
    cin >> n >> m;
    cnt = 1 << n;
    FOR(i, 0, m) {
        int k;
        cin >> k;
        if (k != 0) {
//            s.emplace_back();
        } else {
            has_empty = true;
        }
        int mask = 0;
        FOR(j, 0, k) {
            int q;
            cin >> q;
//            s.back().emplace_back(q);
            mask |= (1 << (--q));
        }
        mask_size[mask] = k;
        useful[mask] = true;
    }
    cout << (main_check() ? "YES" : "NO");
}


int main() {
    fastIO();
    solve();
    return 0;
}