#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "epsilon.in";
    char *out = "epsilon.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    // ios::sync_with_stdio(0);
    // cin.tie(0);
    // cout.tie(0);
}

pair<string, string> readFS() {
    vector<string> vs;
    string s, s1, s2;
    getline(cin, s);
    s1 = s.substr(0, s.size()-2);
    if (s.find('-') == -1) {
        s2 = s[s.size()-1];
        return make_pair(s1, s2);
    } else {
        if (s.size() == 4) {
            s2 = "_";
        } else {
            s2 = s.substr(5);
        }
        return make_pair(s1, s2);
    }
}

set<char> set_eps;
set<pair<char, string>> query;

void get_all_eps() {
    while (true) {
        set<pair<char, string>> new_query;
        for (auto p : query) {
            bool flag = true;
            for (char c : p.second) {
                if (set_eps.find(c) == set_eps.end()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                set_eps.insert(p.first);
            } else {
                new_query.insert(p);
            }
        }
        if (new_query.size() == query.size())
            break;
        else
            query = new_query;
    }
}

int main() {
    open_file_rw();
    pair<string, string> tmp = readFS();
    int n = stoi(tmp.first);
    string start = tmp.second;
    forn(n) {
        pair<string, string> tmp1 = readFS();
        char s = tmp1.first[0];
        if (tmp1.second == "_") {
            set_eps.insert(s);
        } else {
            query.insert(make_pair(s, tmp1.second));
        }
    }
    get_all_eps();
    for (char i : set_eps) {
        cout << i << " ";
    }
}