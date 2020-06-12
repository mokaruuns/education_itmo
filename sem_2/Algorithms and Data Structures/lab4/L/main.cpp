#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;

using namespace std;

void open_file_rw() {
    char *in = "set.in";
    char *out = "set.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

ll A = rand() % 10000 + 1;
ll prime = 10000019;
ll M = 1e6;

list<ll> my_set[1000000];

ll get_hash(ll number) { return ((A * (number + INT32_MAX)) % prime) % M; }

void my_insert(ll number) {
    ll h = get_hash(number);
    if (find(my_set[h].begin(), my_set[h].end(), number) == my_set[h].end()) {
        my_set[h].push_back(number);
    }
}
void my_delete(ll number) {
    ll h = get_hash(number);
    my_set[h].remove(number);
}

bool my_exist(ll number) {
    ll h = get_hash(number);
    for (auto v : my_set[h]) {
        if (v == number) {
            return true;
        }
    }
    return false;
}

int main() {
    open_file_rw();
    string request;
    ll number;
    while (cin >> request >> number) {
        switch (request[0]) {
        case 'i':
            my_insert(number);
            break;
        case 'd':
            my_delete(number);
            break;
        case 'e':
            cout << (my_exist(number) ? "true" : "false") << "\n";
            break;
        }
    }
}