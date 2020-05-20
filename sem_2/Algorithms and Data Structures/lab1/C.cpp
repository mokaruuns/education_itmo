#include <bits/stdc++.h>

#define ll long long
#define endl "\n"
using namespace std;
ll big_int_const = 1000000000000000001;
ll min_int_const = -1000000000000000001;
ll mod = 1000000000000000000;
ll a[500000];
ll a_set[500000];
ll a_inc[500000];
ll shift;

void propagate(ll position, ll left_now, ll right_now) {
    if (a_set[position] != min_int_const) {
        a[position] = a_set[position];
        if (right_now != left_now) {
            a_set[position * 2 + 1] = a_set[position];
            a_set[position * 2 + 2] = a_set[position];
            a_inc[position * 2 + 1] = 0;
            a_inc[position * 2 + 2] = 0;
        }
        a_set[position] = min_int_const;

    }
    if (a_inc[position] != 0) {
        a[position] = (a[position] + a_inc[position]) % mod;
        if (right_now != left_now) {
            a_inc[position * 2 + 1] = (a_inc[position * 2 + 1] + a_inc[position]) % mod;
            a_inc[position * 2 + 2] = (a_inc[position * 2 + 2] + a_inc[position]) % mod;
        }
        a_inc[position] = 0;
    }
}

void update(ll position, ll left_now, ll right_now, ll left, ll right, ll set_val, ll inc_val) {
    propagate(position, left_now, right_now);
    if (left > right_now || right < left_now)
        return;
    if (left_now >= left && right_now <= right) {
        if (set_val != min_int_const) {
            a_set[position] = set_val;
        }
        if (inc_val != 0) {
            a_inc[position] = inc_val;
        }
        propagate(position, left_now, right_now);
        return;
    }
    ll middle = (left_now + right_now) / 2;
    update(position * 2 + 1, left_now, middle, left, right, set_val, inc_val);
    update(position * 2 + 2, middle + 1, right_now, left, right, set_val, inc_val);
    a[position] = min(a[position * 2 + 1], a[position * 2 + 2]);
}


ll get_min(ll position, ll left_now, ll right_now, ll left, ll right) {
    propagate(position, left_now, right_now);
    if (left > right_now || right < left_now)
        return big_int_const;
    if (left_now >= left && right_now <= right) {
        return a[position];
    }
    ll middle = (left_now + right_now) / 2;
    return min(get_min(2 * position + 1, left_now, middle, left, right),
               get_min(2 * position + 2, middle + 1, right_now, left, right));
}

void build(ll l, ll r) {
    if (l != 0) {
        for (ll i = l; i < r; i = i + 2) {
            a[i / 2] = min(a[i], a[i + 1]);
        }
        build(l / 2, r / 2);
    }
}

int main() {
    ll n;
    cin >> n;
    fill(begin(a), end(a), big_int_const);
    fill(begin(a_set), end(a_set), min_int_const);
    shift = (1 << (ll) ceil(log2(n))) - 1;
    for (ll i = 0; i < n; i++) {
        cin >> a[shift + i];
    }
    build(shift, 2 * shift);
//    for (int i = 0; i < 2*shift; i++){
//        cout << a[i] << " ";
//    }
    string s;
    ll i, j;
    while (cin >> s >> i >> j) {
        i += shift;
        j += shift;
        if (s == "min") {
            cout << get_min(0, shift, 2 * shift, i - 1, j - 1) << endl;
        } else {
            ll x;
            cin >> x;
            if (s == "set") {
                update(0, shift, 2 * shift, i - 1, j - 1, x, 0);
            } else {
                update(0, shift, 2 * shift, i - 1, j - 1, min_int_const, x);
            }
        }
    }
} 
