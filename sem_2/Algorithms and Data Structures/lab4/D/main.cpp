#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
    char *in = "sequence.in";
    char *out = "sequence.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}

int main() {
    open_file_rw();
    int n;
    vector<pair<int, int>> vi;
    cin >> n;
    forn(n) {
        int c;
        cin >> c;
        vi.push_back(make_pair(c, i + 1));
    }
    sort(vi.rbegin(), vi.rend());
    vector<pair<int, int>> vi1;
    int s1 = 0, s2 = 0;
    for (auto i : vi) {
        if (s1 < s2) {
            s1 += i.first;
            vi1.push_back(i);
        } else {
            s2 += i.first;
        }
    }
    if (s1 == s2) {
        cout << vi1.size() << endl;
        for (auto i : vi1) {
            cout << i.second << " ";
        }
    } else {
        cout << -1;
    }
}