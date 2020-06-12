#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "map.in";
    char *out = "map.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

ll A = rand() % 10000 + 1;
ll prime = 10000019;
ll M = 1e6;

list<pair<string, string>> my_set[1000000];

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
            (*iter).second = value;
            return;
        }
    }
    my_set[h].push_back(make_pair(key, value));
}
void my_delete(string key, ll h) {
    for (auto iter = my_set[h].begin(); iter != my_set[h].end(); iter++) {
        if ((*iter).first == key) {
            my_set[h].erase(iter);
            return;
        }
    }
}

string my_exist(string key, ll h) {
    for (auto p : my_set[h]) {
        if (p.first == key) {
            return p.second;
        }
    }
    return "none";
}

int main() {
    open_file_rw();
    string request;
    string key, value;
    while (cin >> request >> key) {
        ll h = get_hash_string(key);
        switch (request[0]) {
        case 'p':
            cin >> value;
            my_insert(key, value, h);
            break;
        case 'd':
            my_delete(key, h);
            break;
        case 'g':
            cout << my_exist(key, h) << "\n";
            break;
        }
    }
}