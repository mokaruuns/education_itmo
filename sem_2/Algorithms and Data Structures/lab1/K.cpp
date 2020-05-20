#include <bits/stdc++.h>

#define endl "\n"
typedef long long ll;
using namespace std;

int arr[1000000][2];
int middle;
int k, n;

void update(ll i) {
    ll z = i;
    while (z != 0) {
        z = (z - 1) / 2;
        arr[z][0] = arr[2 * z + 1][0] * arr[2 * z + 1][1];
        arr[z][1] = arr[2 * z + 2][0] * arr[2 * z + 2][1];
    }
}

void set_val(int x, int value) {
    int t;
    if (x > middle) {
        t = x - n;
    } else {
        t = x + n;
    }
    arr[x][0] = value;
    arr[x][1] = value;
    arr[t][0] = value;
    arr[t][1] = value;
    update(x);
    update(t);
    if (value==1) cout << min(x, t) - k << endl;
}

void build(ll l, ll r) {
    if (l != 0) {
        for (int i = l; i < r - 1; i = i + 2) {
            arr[i / 2][0] = arr[i][0] * arr[i][1];
            arr[i / 2][1] = arr[i + 1][0] * arr[i + 1][1];
        }
        build(l / 2, r / 2);
    }
}


void fill(int p, int r) {
    for (int i = p; i < r; i++) {
        arr[i][0] = 1;
        arr[i][1] = 1;
    }
}

bool enter(int pos, int x, int lx, int rx) {
    if (pos >= rx || (arr[x][0] == 1 && arr[x][1] == 1)) {
        return false;
    }
    if (rx - lx == 1) {
        if (arr[x][0] == 0 && arr[x][1] == 0) {
            set_val(x, 1);
            return true;
        } else return false;
    }
    int m = (lx + rx) / 2;
    if (enter(pos, 2 * x + 1, lx, m)) { return true; }
    else if (enter(pos, 2 * x + 2, m, rx)) { return true; }
    return false;
}


int main() {
    freopen("parking.in", "r", stdin);
    freopen("parking.out", "w", stdout);
    int m;
    cin >> n >> m;
    int round_places = 2 * n - 1;
    int exp = ceil(log2(round_places));
    int f = (1 << exp) - 1; // номера в дереве 1 == 7
    k = f - 1;
    int e = (1 << (exp + 1)) - 1; // номер последнего
    fill(f + round_places, e);
    build(f, e);
    middle = (1 << exp) + n - 2; // номер среднего в дереве
    for (int i = 0; i < m; i++) {
        string requests;
        int x;
        cin >> requests >> x;
        x += f - 1;
        if (requests == "enter") {
            enter(x, 0, f, e);
        } else {
            set_val(x, 0);
        }
    }
    return 0;
} 
