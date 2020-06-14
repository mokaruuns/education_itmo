#include <bits/stdc++.h>

// ---------------------------------------------------------------------------------
//                                                       defines
// ---------------------------------------------------------------------------------

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

// ---------------------------------------------------------------------------------
//                                                       variables
// ---------------------------------------------------------------------------------
string word;
int m;
int n;

// ---------------------------------------------------------------------------------
//                                                       system methods
// ---------------------------------------------------------------------------------

void open_file_rw() {
    char *in = "cf.in";
    char *out = "cf.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

bool is_ned(char c) { return 'A' <= c && c <= 'Z'; }

pair<string, string> readFS() {
    vector<string> vs;
    string s, s1, s2;
    getline(cin, s);
    s1 = s.substr(0, s.size() - 2);
    if (s.find('-') == -1) {
        s2 = s[s.size() - 1];
        return make_pair(s1, s2);
    } else {
        if (s.size() == 4) {
            s2 = "";
        } else {
            s2 = s.substr(5);
        }
        return make_pair(s1, s2);
    }
}

// ---------------------------------------------------------------------------------
//                                                KS to CNF
// ---------------------------------------------------------------------------------

map<string, set<pair<string, string>>>
remove_long_rules(map<string, set<string>> mp) {
    int global_number_value = 0;
    map<string, set<pair<string, string>>> new_mp;
    for (auto st : mp) {
        for (string rule : st.second) {
            if (rule.size() > 2) {
                string current_st = st.first;
                forn(rule.size() - 1) {
                    string new_st = "X" + to_string(global_number_value++);
                    string tmp_str(1, rule[i]);
                    new_mp[current_st].insert(make_pair(tmp_str, new_st));
                    current_st = new_st;
                }
                string tmp_str1(1, rule[rule.size() - 1]);
                string tmp_str2(1, rule[rule.size() - 2]);
                new_mp[current_st].insert(make_pair(tmp_str1, tmp_str2));
            }
        }
    }
    for (auto st: new_mp) {
        for (auto rules : st.second) {
            cout << st.first << " -> " << rules.first << " " << rules.second << endl;
        }
    }
}
void remove_eps_rules() {}
void remove_chain_rules() {}
void remove_uselles_rules() {}
void remove_many_terminals() {}

void ks_to_cnf(map<string, set<string>> mp) {
    remove_long_rules(mp);
    remove_eps_rules();
    remove_chain_rules();
    remove_uselles_rules();
    remove_many_terminals();
}

// ---------------------------------------------------------------------------------
//                                                count
// ---------------------------------------------------------------------------------

void get_count(map<string, set<string>> mp) {
    map<string, ll[101][101]> my_d;

    for (int i = 0; i < n; i++) {
        for (auto st : mp) {
            string ss(1, word[i]);
            if (st.second.find(ss) != st.second.end()) {
                my_d[st.first][i][i] = 1;
            }
        }
    }
    // for (int len = 1; len < n; len++) {
    //     for (int i = 0; i < n - len; i++) {
    //         for (auto st : mp) {
    //             for (string s : st.second) {

    //                 if (s.size() == 2) {
    //                     for (int k = 0; k < len; k++) {
    //                         my_d[st.first][i][i + len] =
    //                             (my_d[st.first][i][i + len] +
    //                              my_d[s[0]][i][i + k] *
    //                                  my_d[s[1]][i + k + 1][i + len]) %
    //                             (int)(1e9 + 7);
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }
}

// ---------------------------------------------------------------------------------
//                                               main
// ---------------------------------------------------------------------------------

int main() {
    map<string, set<string>> mp;
    open_file_rw();
    pair<string, string> tmp = readFS();
    m = stoi(tmp.first);
    string start = tmp.second;
    forn(m) {
        pair<string, string> tmp1 = readFS();
        string c = tmp1.first;
        string s = tmp1.second;
        mp[c].insert(s);
    }

    ks_to_cnf(mp);

    // cin >> word;
    // n = word.size();
    // get_count();
    // cout << my_d[start[0]][0][n - 1];
}