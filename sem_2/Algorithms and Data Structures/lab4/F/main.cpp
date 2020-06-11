#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;
long long cnt = 0;
int a[100001];

void mergee(int l, int mid, int r) {
    int i = l;
    int j = mid;
    int n = mid;
    int m = r;
    vector<int> c;
    while (i + j < n + m) {
        if ((j == m) || ((i < n) && (a[i] <= a[j]))) {
            c.push_back(a[i]);
            i++;
        } else {
            c.push_back(a[j]);
            j++;
            cnt += (n - i);
        }
    }
    for (int k = l; k < r; k++) {
        a[k] = c[k - l];
    }
}
void sort(int l, int r) {
    if (r - l <= 1) {
        return;
    }
    int m = (l + r) / 2;
    sort(l, m);
    sort(m, r);
    mergee(l, m, r);
}

void open_file_rw() {
    char *in = "john.in";
    char *out = "john.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}
bool comp(pair<int, int> a, pair<int, int> b) {
    if (a.first == b.first) {
        return a.second < b.second;
    }
    return a.first < b.first;
}

int main() {
    open_file_rw();
    int n;
    cin >> n;
    vector<pair<int, int>> vi(n);
    forn(n) {
        int a, b;
        cin >> a >> b;
        vi[i] = make_pair(a, b);
    }
    sort(vi.begin(), vi.end(), comp);
    forn(n) { a[i] = vi[i].second; }
    sort(0, n);
    cout << cnt;
}