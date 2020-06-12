#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "multimap.in";
    char *out = "multimap.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

ll A = rand() % 10000 + 1;
ll prime = 10000019;
ll M = 1e5;

list<pair<string, list<string>>> my_set[100000];

// ll get_hash(ll number) { return ((A * (number + INT32_MAX)) % prime) % M; }
ll get_hash_string(string str) {
    ull h = 0;
    for (char c : str) {
        h = 33 * h ^ c;
    }
    return h % M;
}

void my_insert(string key, string value, ll h) {
    for (auto iter = my_set[h].begin(); iter != my_set[h].end(); iter++) {
        if ((*iter).first == key) {
            for (auto iter_val : (*iter).second) {
                if (iter_val == value) {
                    return;
                }
            }
            ((*iter).second).push_back(value);
            return;
        }
    }
    list<string> vs;
    vs.push_back(value);
    my_set[h].push_back(make_pair(key, vs));
    // cout << key << " " << value << endl;
}

void my_delete(string key, string value, ll h) {
    for (auto iter = my_set[h].begin(); iter != my_set[h].end(); iter++) {
        if ((*iter).first == key) {
            for (auto iter_sec = (*iter).second.begin(); iter_sec != (*iter).second.end(); iter_sec++){
                if (*iter_sec==value){
                    (*iter).second.erase(iter_sec);
                    return;
                }
            }
        }
    }
}

void my_delete_all(string key, ll h) {
    for (auto iter = my_set[h].begin(); iter != my_set[h].end(); iter++) {
        if ((*iter).first == key) {
            my_set[h].erase(iter);
            return;
        }
    }
}

list<string> my_exist(string key, ll h) {
    list<string> vs;
    for (auto p : my_set[h]) {
        if (p.first == key) {
            return p.second;
        }
    }
    return vs;
}

int main() {
    open_file_rw();
    string request;
    string key, value;
    while (cin >> request >> key) {
        ll h = get_hash_string(key);
        if (request == "put") {
            cin >> value;
            my_insert(key, value, h);
        } else if (request == "delete") {
            cin >> value;
            my_delete(key, value, h);
        } else if (request == "deleteall") {
            my_delete_all(key, h);
        } else if (request == "get") {
            list<string> vi = my_exist(key, h);
            cout << vi.size() << " ";
            for (auto s : vi) {
                cout << s << " ";
            }
            cout << "\n";
        }
    }
}