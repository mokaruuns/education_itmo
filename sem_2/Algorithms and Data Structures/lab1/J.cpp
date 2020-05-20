#include <bits/stdc++.h>

#define LL long long
#define ENDL "\n"
using namespace std;
pair<int, int> t[5000000]; // t[0]-позиция минимального, t[1] - минимум
int tSet[5000000];


void build(int lp, int rp) {
    if (lp != 0) {
        for (int i = lp; i < rp; i = i + 2) {
            t[i / 2].first = t[i].first;
        }
        build(lp / 2, rp / 2);
    }
}

void propagate(int p, int lp, int rp) {
    if (tSet[p] != -1) {
        if (tSet[p] > t[p].second) {
            t[p].second = tSet[p];
        }
        if (rp != lp) {
            if (t[2 * p + 1].second < tSet[p]) {
                tSet[p * 2 + 1] = tSet[p];
                t[2 * p + 1].second = tSet[p];
            }
            if (t[2 * p + 2].second < tSet[p]) {
                tSet[p * 2 + 2] = tSet[p];
                t[2 * p + 2].second = tSet[p];
            }
        }
        tSet[p] = -1;
    }
}

void update(int p, int lp, int rp, int l, int r, int value) {
    propagate(p, lp, rp);
    if (l > rp || r < lp)
        return;
    if (lp >= l && rp <= r) {
        tSet[p] = value;
        propagate(p, lp, rp);
        return;

    }
    int middle = (lp + rp) / 2;
    update(p * 2 + 1, lp, middle, l, r, value);
    update(p * 2 + 2, middle + 1, rp, l, r, value);
    if (t[p * 2 + 1].second < t[p * 2 + 2].second) {
        t[p].second = t[p * 2 + 1].second;
        t[p].first = t[p * 2 + 1].first;
    } else {
        t[p].second = t[p * 2 + 2].second;
        t[p].first = t[p * 2 + 2].first;
    }
}

pair<int, int> get_min(int p, int lp, int rp, int l, int r) {
    propagate(p, lp, rp);
    if (l > rp || r < lp)
        return pair<int, int>(-1, INT_MAX);
    if (lp >= l && rp <= r) {
        return t[p];
    }
    int middle = (lp + rp) / 2;
    pair<int, int> lx = get_min(2 * p + 1, lp, middle, l, r);
    pair<int, int> rx = get_min(2 * p + 2, middle + 1, rp, l, r);
    if (lx.second < rx.second) {
        return lx;
    } else {
        return rx;
    }
}

int main() {
    int n, m;
    cin >> n >> m;
    fill(begin(tSet), end(tSet), -1);
    int shift = (1 << (int) ceil(log2(n))) - 1;
    for (int i = shift - 1; i < 2 * shift + 1; i++) {
        t[i].first = i;
        t[i].second = 0;
    }
    build(shift, shift * 2);
    for (int i = 0; i < m; i++) {
        string s;
        cin >> s;
        if (s == "defend") {
            int a, b, c;
            cin >> a >> b >> c;
            a += shift;
            b += shift;
            update(0, shift, 2 * shift, a - 1, b - 1, c);
        } else {
            int d, e;
            cin >> d >> e;
            d += shift;
            e += shift;
            pair<int, int> p = get_min(0, shift, 2 * shift, d - 1, e - 1);
            cout << p.second << " " << p.first - shift + 1 << ENDL;
        }
    }
} 
