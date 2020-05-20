#include <cstdio>
#include <iostream>
#include <map>
#include <set>

using namespace std;


void use_file(const char *fin, const char *fout) {
    freopen(fin, "r", stdin);
    freopen(fout, "w", stdout);
}


void read_nka(map<int, map<char, int>> *dka, set<int> *terminated, set<char> *A) {
    int n, m, k;
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++) {
        int tmp;
        cin >> tmp;
        (*terminated).insert(tmp);
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        (*dka)[a][c] = b;
        A->insert(c);
    }
}

set<int> diff(const set<int> &Q, const set<int> &T) {
    set<int> R;
    for (auto x: Q) if (T.find(x) == T.end()) R.insert(x);
    return R;
}

pair<set<int>, set<int>> split(const set<int> &R, const set<int> &C, char a, map<int, map<char, int>> dka) {
    set<int> RT, RF;
    for (auto r: R) {
        if (C.find(dka[r][a]) != C.end()) {
            RT.insert(r);
        } else {
            RF.insert(r);
        }
    }
    return make_pair(RT, RF);
}

void get_dka(const set<set<int>> &P, map<int, map<char, int>> dka, const set<int> &started,
             const set<int> &terminated,
             const set<char> &A) {
    map<int, map<char, int>> dka_answer;
    map<int, set<int>> m;
    map<int, int> useful;
    set<int> terminated_answer;
    int n = 0;
    for (const auto &p: P) {
        if (!p.empty()) {
            n++;
            m[n] = p;
            for (auto x: p) {
                useful[x] = n;
            }
        }
    }
    int t = 0;
    for (const auto &p: P) {
        int a = *p.begin();
        if (terminated.find(a) != terminated.end()) terminated_answer.insert(useful[a]);
        for (auto y: dka[a]) {
            char c = y.first;
            int b = y.second;
            dka_answer[useful[a]][c] = useful[b];
            t++;
        }
    }
    if (terminated_answer.empty() || dka_answer.empty() || t == 0) { exit(1); }
    cout << dka_answer.size() << " " << t << " " << terminated_answer.size() << endl;
    for (auto i : terminated_answer) cout << i << " ";
    cout << endl;
    for (const auto &a: dka_answer) {
        for (auto x: a.second) {
            cout << a.first << " " << x.second << " " << x.first << endl;
        }
    }
}

void get_minimization(const map<int, map<char, int>> &dka, const set<int> &started, const set<int> &terminated,
                      const set<char> &A) {
    set<int> Q;
    set<set<int>> P;
    set<pair<set<int>, char>> S;
    for (const auto &x: dka) if (!x.second.empty()) Q.insert(x.first);
    set<int> QDT = diff(Q, terminated);
    if (!terminated.empty()) P.insert(terminated);
    if (!QDT.empty())P.insert(QDT);
    for (auto c : A) {
        if (!terminated.empty())S.insert(make_pair(terminated, c));
        if (!QDT.empty())S.insert(make_pair(QDT, c));
    }
    while (!S.empty()) {
        pair<set<int>, char> pair_C_a = *S.begin();
        S.erase(pair_C_a);
        for (const auto &R: P) {
            pair<set<int>, set<int>> r = split(R, pair_C_a.first, pair_C_a.second, dka);
            if (!r.first.empty() && !r.second.empty()) {
                P.erase(R);
                P.insert(r.first);
                P.insert(r.second);
                if (S.find(make_pair(R, pair_C_a.second)) != S.end()) {
                    S.erase(make_pair(R, pair_C_a.second));
                    S.insert(make_pair(r.first, pair_C_a.second));
                    S.insert(make_pair(r.second, pair_C_a.second));
                } else {
                    if (r.first.size() <= r.second.size()) {
                        S.insert(make_pair(r.first, pair_C_a.second));
                    } else {
                        S.insert(make_pair(r.second, pair_C_a.second));
                    }
                }
            }
        }
    }
    get_dka(P, dka, started, terminated, A);
}


map<int, map<char, int>> dka;
set<int> started;
set<int> terminated;
set<char> A;

int main() {
    use_file("minimization.in", "minimization.out");
    read_nka(&dka, &terminated, &A);
    get_minimization(dka, started, terminated, A);
}