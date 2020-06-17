#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
#define forc(finish) for (int c = 0; c < finish; c++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "cf.in";
    char *out = "cf.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

bool is_term(char c) { return 'a' <= c && c <= 'z'; }

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
            s2 = "_";
        } else {
            s2 = s.substr(5);
        }
        return make_pair(s1, s2);
    }
}

char Start;
map<char, bool[101][101]> my_d;
map<pair<char, string>, bool[101][101][101]> h;

bool cyk_aaa(string word, map<char, set<string>> mp) {
    int n = word.size();
    int M = 0;
    for (int i = 0; i < n; i++) {
        for (auto st : mp) {
            string ss(1, word[i]);
            my_d[st.first][i][i + 1] = (st.second.find(ss) != st.second.end());
            my_d[st.first][i][i] = (st.second.find("_") != st.second.end());
            for (auto rule : st.second) {
                h[make_pair(st.first, rule)][i][i][0] = true;
                M = max(M, (int) rule.length());
            }
        }
    }

    forc(30){
        for (int m = 0; m <= n; m++) {
            for (int i = 0; i < n; i++) {
                int j = i + m;
                if (j > n)
                    break;
                for (int pos = 1; pos <= M; pos++) {
                    for (auto st : mp) {
                        for (auto rule : st.second) {
                            if (rule.length() < pos)
                                continue;
                            for (int key = i; key <= j; key++) {
                                if (is_term(rule[pos - 1])) {
                                    h[make_pair(st.first, rule)][i][key][pos] |=
                                            h[make_pair(st.first, rule)][i][key]
                                            [pos - 1] &&
                                            (key == j - 1) &&
                                            (word[key] == rule[pos - 1]);
                                } else {
                                    h[make_pair(st.first, rule)][i][key][pos] |=
                                            h[make_pair(st.first, rule)][i][key]
                                            [pos - 1] &&
                                            (my_d[rule[pos - 1]][key][j]);
                                }
                            }
                        }
                    }
                }
                for (auto st : mp) {
                    for (auto rule : st.second) {
                        my_d[st.first][i][j] |=
                                h[make_pair(st.first, rule)][i][j][rule.length()];
                    }
                }
            }
        }
    }

    return my_d[Start][0][n];
}

int main() {
    map<char, set<string>> mp;
    open_file_rw();
    pair<string, string> tmp = readFS();
    int m = stoi(tmp.first);
    Start = tmp.second[0];
    forn(m) {
        pair<string, string> tmp1 = readFS();
        char c = tmp1.first[0];
        string s = tmp1.second;
        mp[c].insert(s);
    }
    string word;
    cin >> word;
    cout << (cyk_aaa(word, mp) ? "yes" : "no");
}