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
    vector<int> z(str.size() + 1, 0);
    int left = 0;
    FOR(i, 1, str.size()) {
        z[i] = max(0, min(z[i - left], z[left] + left - i));
        while (str[i + z[i]] == str[z[i]]) {
            z[i]++;
        }
        if (i + z[i] > left + z[left]) {
            left = i;
        }
    }
    FOR(i, 1, str.size())cout << z[i] << " ";
}

int main() {
    fastIO();
    solve();
    return 0;
}
