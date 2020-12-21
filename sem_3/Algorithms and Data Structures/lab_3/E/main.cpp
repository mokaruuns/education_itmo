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
    string str;
    cin >> str;
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
    int z = p[str.size()];
    if (str.size() % (str.size() - z) == 0) {
        cout << str.size() - z;
    } else {
        cout << str.size();
    };
}

int main() {
    fastIO();
    solve();
    return 0;
}