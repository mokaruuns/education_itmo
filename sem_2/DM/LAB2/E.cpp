#include <bits/stdc++.h>

using namespace std;
int n, m, k, l;
map<int, set<int>> misi;

int in_misi(const set<int> &si) {
    for (const auto &pair_misi: misi) {
        if (si == pair_misi.second) {
            return pair_misi.first;
        }
    }
    return -1;
}


pair<vector<map<char, int>>, map<int, bool>>
nka_to_dka(vector<map<char, vector<int>>> go_nka, map<int, bool> starts, map<int, bool> terminated_first) {
    vector<map<char, int>> go_dka(1001);
    map<int, bool> terminated_second;
    queue<int> que; // очередь с номерами новых вершин
    for (auto st: starts) {
        que.push(st.first);
        in_misi({st.first});
        terminated_second[st.first] = terminated_first[st.first];
        misi[st.first] = {st.first};
    }

    while (!que.empty()) {
        int start = que.front();
        que.pop();
        set<int> current_set = misi[start];
        map<char, set<int>> mcvi;
        map<char, bool> mcvi_term;
        for (auto cur: current_set) {
            for (const auto &x: go_nka[cur]) {
                for (auto y: x.second) {
                    mcvi[x.first].insert(y);
                    mcvi_term[x.first] |= terminated_first[y];
                }
            }
        }
        for (const auto &s: mcvi) {
            int id = in_misi(s.second);
            if (id == -1) {
                id = misi.size() + 1;
                misi[id] = s.second;
                que.push(id);
                terminated_second[id] = mcvi_term[s.first];
            }
            go_dka[start][s.first] = id;
        }
    }
    return make_pair(go_dka, terminated_second);
}

int cnt[1001][1001];
vector<map<char, vector<int>>> go_nka(1001);

int main() {
    freopen("problem5.in", "r", stdin);
    freopen("problem5.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    map<int, bool> terminated;
    cin >> n >> m >> k >> l;
    for (int i = 0; i < k; i++) {
        int tmp;
        cin >> tmp;
        terminated[tmp] = true;
    }

    map<int, bool> starts;
    starts[1] = true;
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        go_nka[a][c].push_back(b);
    }
    pair<vector<map<char, int>>, map<int, bool>> p = nka_to_dka(go_nka, starts, terminated);
    vector<map<char, int>> go_dka = p.first;
    map<int, bool> terminated_second = p.second;
    cnt[1][0] = 1;
    int cnst = 1e9 + 7;
    for (int j = 0; j < l; j++) {
        for (int i = 1; i <= misi.size(); i++) {
            for (auto x : go_dka[i]) {
                cnt[x.second][j + 1] = (cnt[x.second][j + 1] + cnt[i][j]) % cnst;
            }
        }
    }
    int sum = 0;
    for (int i = 1; i <= misi.size(); i++) {
        sum = (sum + terminated_second[i] * cnt[i][l]) % cnst;
    }
    cout << sum;
}