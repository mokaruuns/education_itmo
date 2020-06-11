#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
    char *in = "printing.in";
    char *out = "printing.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}

int main() {
    open_file_rw();
    vector<int> vi(7, INT32_MAX);
    int count;
    cin >> count;
    forn(7) {
        int cost;
        cin >> cost;
        forj(i, 7) { vi[j] = min((int)pow(10, j - i) * cost, vi[j]); }
    }
    int min_sum_one = INT32_MAX;
    int min_sum_many = 0;
    int c1 = count;
    for (int i = 6; i >= 0; i--) {
        int k = c1 / pow(10, i);
        min_sum_many += vi[i] * k;
        c1 -= pow(10, i) * k;
        if (c1 > 0) {
            min_sum_one = min(min_sum_one, min_sum_many + vi[i]);
        }
    }
    cout << min(min_sum_many, min_sum_one);
}