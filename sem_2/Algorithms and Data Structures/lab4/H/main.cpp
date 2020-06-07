#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
    char *in = "vectors2.in";
    char *out = "vectors2.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}

int main() {
    open_file_rw();
    int n;
    cin >> n;
    vector<int> vi;
    forn(1 << n) {
        bool isOk = false;
        for (int j = 0; j < 15; j++) {
            isOk |= __builtin_popcount(i & (3 << j)) == 2;
        }
        if (!isOk) {
            vi.push_back(i);
        }
    }
    cout << vi.size() << "\n";
    for (auto j : vi) {
        for (int i = n-1; i >= 0; --i)
            cout << ((j >> i) & 1);
        cout << "\n";
    }
}