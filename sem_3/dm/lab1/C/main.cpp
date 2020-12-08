#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;
char graph[4001][4001];

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

bool comp(int x, int y) {
    cout << 1 << " " << x << " " << y << endl;
    cout.flush();
    string answer;
    cin >> answer;
    return answer == "YES";
}

void solve() {
    int n;
    cin >> n;
    vector<int> vi;
    FOR(i, 1, n + 1) vi.push_back(i);
    stable_sort(vi.begin(), vi.end(), comp);
    cout << 0 << " ";
    for (int v: vi) cout << v << " ";
}

int main() {
    fastIO();
    solve();
    return 0;
}
