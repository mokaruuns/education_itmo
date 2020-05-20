#include <bits/stdc++.h>

typedef long long ll;
using namespace std;
ll arr[5000001];

void build(ll l, ll r) {
    if (l != 0) {
        for (int i = l; i < r - 1; i = i + 2) {
            arr[i / 2] = arr[i] + arr[i + 1];
        }
        build(l / 2, r / 2);
    }
}

ll sum(ll position, ll left_now, ll right_now, ll a, ll b) { // [a,b] границы нашего массива
    if (a > right_now || b < left_now)
        return 0;
    if (left_now >= a && right_now <= b) {
        return arr[position];
    }
    ll middle = (left_now + right_now) / 2;
    return sum(2 * position + 1, left_now, middle, a, b) + sum(2 * position + 2, middle + 1, right_now, a, b);
}

void set_value(ll i, ll value) { // [a,b] наш отрезок
    arr[i] = value;
    ll z = i;
    while (z != 0) {
        z = (z - 1) / 2;
        arr[z] = arr[2 * z + 1] + arr[2 * z + 2];
    }
}

int main() {
    ll n;
    cin >> n;
    ll p = ceil(log2(n));
    for (int i = 0; i < n; i++) {
        cin >> arr[i + (1 << p) - 1];
    }
    build((1 << p) - 1, (1 << (p + 1)));
    string s;
    ll i, x;
    while (cin >> s >> i >> x) {
        if (s == "set") {
            set_value((1 << p) - 2 + i, x);
//            for (int i = 0; i < (1<<p+1); i++) {
//                cout << arr[i] << " ";
//            }
        } else {
            cout << sum(0, 0, (1 << p) - 1, i - 1, x - 1) << endl;
        }
    }
    return 0;
}
 
