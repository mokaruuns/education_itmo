#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;


using namespace std;
char graph[1001][1001];

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void find_cycle(deque<int> &deque, int size) {

}

void solve() {
    int n;
    cin >> n;
    deque<int> deque;
    FOR(i, 1, n) {
        deque.emplace_back(i);
        FOR(j, 0, i) {
            char isEdge;
            cin >> isEdge;
            if (isEdge) {
                graph[i + 1][j + 1] = 1;
            } else {
                graph[j + 1][i + 1] = 1;
            }
        }
    }
    deque.emplace_back(n);
    find_cycle(deque, n);
}

int main() {
    fastIO();
    solve();
    return 0;
}
