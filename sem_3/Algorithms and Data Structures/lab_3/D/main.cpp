#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef unsigned long long ll;
using namespace std;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void solve() {
    string small, big;
    cin >> small >> big;
    string str = small + "_" + big;
    vector<int> p(str.size() + 1, 0);
    p[0] = -1;
    FOR(i, 1, str.size() + 1) {
        int k = p[i - 1];
        while (k >= 0) {
            if (str[k] == str[i - 1]) {
                p[i] = k + 1;
                break;
            }
            k = p[k];
        }
    }
    vector<int> ans;
    FOR(i, small.size() + 1, str.size() + 1) {
        if (p[i] == small.size()) {
            ans.emplace_back(i - small.size());
        }
    }
    cout << ans.size() << endl;
    for(int i: ans) {
        cout << i - small.size()<< " ";
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}