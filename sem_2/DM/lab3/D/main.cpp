#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "nfc.in";
    char *out = "nfc.out";
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
            s2 = "_";
        } else {
            s2 = s.substr(5);
        }
        return make_pair(s1, s2);
    }
}
string word;
int m;
int n;
map<char, set<string>> mp;
map<char, ll[101][101]> my_d;

void get_count() {
    for (int i = 0; i < n; i++) {
        for (auto st : mp) {
            string ss(1, word[i]);
            if (st.second.find(ss) != st.second.end()) {
                my_d[st.first][i][i] = 1;
            }
        }
    }
    for (int len = 1; len < n; len++) {
        for (int i = 0; i < n - len; i++) {
            for (auto st : mp) {
                for (string s : st.second) {
                    if (s.size() == 2) {
                        for (int k = 0; k < len; k++) {
                            my_d[st.first][i][i + len] =
                                (my_d[st.first][i][i + len] +
                                 my_d[s[0]][i][i + k] *
                                     my_d[s[1]][i + k + 1][i + len]) %
                                (int)(1e9 + 7);
                        }
                    }
                }
            }
        }
    }
}

int main() {
    open_file_rw();
    pair<string, string> tmp = readFS();
    m = stoi(tmp.first);
    string start = tmp.second;
    forn(m) {
        pair<string, string> tmp1 = readFS();
        char c = tmp1.first[0];
        string s = tmp1.second;
        mp[c].insert(s);
    }
    cin >> word;
    n = word.size();
    get_count();
    cout << my_d[start[0]][0][n - 1];
}