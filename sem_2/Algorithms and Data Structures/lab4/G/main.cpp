#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
    char *in = "beautiful.in";
    char *out = "beautiful.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}

int get_sum(vector<int> vi) {
    int s = 0;
    forn(vi.size() - 1) { s += vi[i] * vi[i + 1]; }
    return s;
}

int arr[1001];

int main() {
    for (int i = 1; i <= 1000; i++) {
        for (int j = 1; j <= i; j++) {
            arr[i] += (i % j == 0);
        }
    }
    open_file_rw();
    int n, r;
    cin >> n >> r;
    vector<int> vi;
    forn(n) { vi.push_back(i + 1); }
    int k = 0;
    do {
        int s = get_sum(vi) % r;
        k += (arr[s] % 3 == 0);
    } while (next_permutation(vi.begin(), vi.end()));
    cout << k;
}