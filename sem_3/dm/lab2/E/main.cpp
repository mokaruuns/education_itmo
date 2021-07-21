#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("cycles.in", "r", stdin);
    freopen("cycles.out", "w", stdout);
}

int MAX_SIZE = 1 << 20 + 1;

vector<bool> useful(MAX_SIZE, false);
vector<int> weights(MAX_SIZE, 0);

void solve() {
    int n, m;
    cin >> n >> m;
    int cnt = 1 << n;
    FOR(i, 0, n) {
        cin >> weights[i];
    }
    FOR(i, 0, m) {
        int k;
        cin >> k;
        int mask = 0;
        FOR(j, 0, k) {
            int q;
            cin >> q;
            mask |= (1 << (--q));
        }
        useful[mask] = true;
    }
    int base = 0;
    FOR(i, 0, cnt) {
        int x;
        if (useful[i]) {
            FOR(j, 0, n) {
                x = 1 << j;
                useful[x | i] = true;
            }
        } else {
            int tmp = 0;
            FOR(j, 0, n) {
                x = 1 << j;
                if (x & i) tmp += weights[j];
            }
            base = max(base, tmp);
        }
    }
    cout << base;
}


int main() {
    fastIO();
    solve();
    return 0;
}