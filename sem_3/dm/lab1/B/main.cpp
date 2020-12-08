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

void find_cycle(deque<int> &deque, int size) {
    FOR(k, 0, size * (size - 1)) {
        if (graph[deque.at(0)][deque.at(1)] == '0') {
            int i = 2;
            while ((i < size - 1) &&
                   (graph[deque.at(0)][deque.at(i)] == '0' || graph[deque.at(1)][deque.at(i + 1)] == '0')) {
                i++;
            }
            if (i == size - 1) {
                i = 2;
                while (graph[deque.at(0)][deque.at(i)] == '0') i++;
            }
            reverse(deque.begin() + 1, deque.begin() + i + 1);
        }
        deque.emplace_back(deque.front());
        deque.pop_front();
    }

    for (int vertex : deque) {
        cout << vertex << " ";
    }
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
            graph[i + 1][j + 1] = isEdge;
            graph[j + 1][i + 1] = isEdge;
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
