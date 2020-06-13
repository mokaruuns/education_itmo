#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "automaton.in";
    char *out = "automaton.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

map<char, vector<pair<char, char>>> mp;
string word;

bool is_right_word();

bool is_lowercase(int position, char current) {
    return current == word[position];
}
bool is_right_word(int position, char current);

bool is_uppercase(int position, char current) {
    if (position == word.size() && current == '_') {
        return true;
    }
    return is_right_word(position, current);
}



bool is_right_word(int position, char current) {
    for (auto v : mp[current]) {
        if (!is_lowercase(position, v.first)) continue;
        if (is_uppercase(position + 1, v.second)) {
            return true;
        }
    }
    return false;
}

int main() {
    open_file_rw();
    int n, m;
    char start;
    cin >> n >> start;
    forn(n) {
        char c;
        string tmp, str;
        cin >> c >> tmp >> str;
        str += "_";
        mp[c].push_back(make_pair(str[0], str[1]));
    }
    cin >> m;
    forn(m) {
        cin >> word;
        cout << (is_right_word(0, start) ? "yes" : "no") << endl;
    }
}