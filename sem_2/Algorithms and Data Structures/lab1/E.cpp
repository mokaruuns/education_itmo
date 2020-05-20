#include <bits/stdc++.h>

#define endl "\n"
typedef long long ll;
using namespace std;
array<array<int, 4>, 800000> arr;
int r;

array<int, 4> multi(array<int, 4> a, array<int, 4> b) {
    array<int, 4> c;
    c[0] = ((a[0] * b[0]) % r + (a[1] * b[2]) % r) % r;
    c[1] = ((a[0] * b[1]) % r + (a[1] * b[3]) % r) % r;
    c[2] = ((a[2] * b[0]) % r + (a[3] * b[2]) % r) % r;
    c[3] = ((a[2] * b[1]) % r + (a[3] * b[3]) % r) % r;
    return c;
}

void build(ll l, ll r) {
    if (l != 0) {
        for (int i = l; i < r - 1; i = i + 2) {
            arr[i / 2] = multi(arr[i], arr[i + 1]);
        }
        build(l / 2, r / 2);
    }
}

array<int, 4> sum(ll position, ll left_now, ll right_now, ll a, ll b) { // [a,b] границы нашего массива
    if (a > right_now || b < left_now)
        return {1, 0, 0, 1};
    if (left_now >= a && right_now <= b) {
        return arr[position];
    }
    ll middle = (left_now + right_now) / 2;
    array<int, 4> vi1 = sum(2 * position + 1, left_now, middle, a, b);
    array<int, 4> vi2 = sum(2 * position + 2, middle + 1, right_now, a, b);
    return multi(vi1, vi2);
}

int main() {
    int n, m;
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    freopen("crypto.in", "r", stdin);
    freopen("crypto.out", "w", stdout);
    cin >> r >> n >> m;
    ll p = ceil(log2(n));
    ll pos = 1 << p;
    for (int i = 0; i < n; i++) {
        ll position = i + pos - 1;
        cin >> arr[position][0];
        cin >> arr[position][1];
        cin >> arr[position][2];
        cin >> arr[position][3];
    }
    build((1 << p) - 1, (1 << (p + 1)));
    for (int i = 0; i < m; i++) {
        int left, right;
        cin >> left >> right;
        array<int,4> vi = sum(0, 0, (1 << p) - 1, left - 1, right - 1);
        cout << vi[0] << " " << vi[1] << endl;
        cout << vi[2] << " " << vi[3] << endl << endl;

    }
    return 0;
} 
