#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "useless.in";
    char *out = "useless.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

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

set<char> borned_states;
map<char, vector<string>> all_states;
map<char, vector<string>> all_states_second;
map<char, vector<string>> all_states_third;

bool is_inlude_net(string s) {
    for (char c : s) {
        if ('A' <= c && c <= 'Z') {
//            cout << s << "+";
            return true;
        }
    }
    return false;
}

bool is_net(char c) {
    return 'A' <= c && c <= 'Z';
}

void find_borned() {
    while (true) {
        int size_set = borned_states.size();
        for (auto st : all_states) {
            for (auto rule : st.second) {
                bool flag = true;
                for (char c : rule) {
                    if (is_net(c) && borned_states.find(c) == borned_states.end()) { // not include
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    borned_states.insert(st.first);
                }
            }
        }
        if (size_set == borned_states.size())
            break;
    }
    for (auto rules: all_states) {
        for (auto v: rules.second) {
            bool flag = true;
            for (char c: v) {
                if (is_net(c) && borned_states.find(c) == borned_states.end()) {
                    flag = false;
                }
            }
            if (flag) {
                all_states_second[rules.first].push_back(v);
            }
        }
    }
}

set<char> finished_states;
set<char> result_set;

void find_finished() {
    while (true) {
        int size_set = finished_states.size();
        for (auto st : borned_states) {
            if (finished_states.find(st) != finished_states.end()) {
                for (auto rule : all_states_second[st]) {
                    for (char c : rule) {
                        if (is_net(c)) {
                            finished_states.insert(c);
                        }
                    }
                }
            }
        }
        if (size_set == finished_states.size())
            break;
    }
    for (auto rules: all_states_second) {
        if (finished_states.find(rules.first) != finished_states.end()) {
            all_states_third.insert(rules);
            result_set.insert(rules.first);
            for (auto v: rules.second) {
                for (char c: v) {
                    if (is_net(c)) {
                        result_set.insert(c);
                    }
                }
            }
        }
    }
}

set<char> all_all_states;

int main() {
    open_file_rw();
    pair<string, string> tmp = readFS();
    int n = stoi(tmp.first);
    string start = tmp.second;
    finished_states.insert(start[0]);
    all_all_states.insert(start[0]);
    forn(n) {
        pair<string, string> tmp1 = readFS();
        char c = tmp1.first[0];
        string s = tmp1.second;
        if (!is_inlude_net(s)) {
            borned_states.insert(c);
        }
        all_all_states.insert(c);
        for (auto all : s) {
            if (is_net(all)) {
                all_all_states.insert(all);
            }
        }
        all_states[c].push_back(s);
    }
    find_borned();
    find_finished();
    for (auto v : all_all_states) {
        if (result_set.find(v) == result_set.end()) {
            cout << v << " ";
        }
    }
}