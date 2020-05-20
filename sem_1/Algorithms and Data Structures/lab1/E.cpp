#include <bits/stdc++.h>
#define ll long long
using namespace std;

int a[500001];

void mergee(int l, int mid, int r) {
    int i = l;
    int j = mid;
    int n = mid;
    int m = r;
    vector <int> c;
    while (i + j < n + m) {
        if ((j==m) || ((i < n) && (a[i] < a[j]))) {
            c.push_back(a[i]);
            i++;
        } else {
            c.push_back(a[j]);
            j++;
        }
    }
    for (int k = l; k < r; k++) {
        a[k] = c[k-l];
    }
}

void sort(int l, int r) {
    if (r-l<=1) {
        return ;
    }
    int m = (l+r)/2;
    sort(l,m);
    sort(m,r);
    mergee(l,m,r);
}

int binaryLeft(int val, ll l, ll r) {
    while (r-l>1) {
        ll m = (l+r)/2;
        if (a[m]<=val) {
            l = m;
        } else {
            r = m;
        }
    }
    return r;
}
int binaryRight(int val, ll l, ll r) {
    while (r-l>1) {
        ll m = (l+r)/2;
        if (a[m]<val) {
            l = m;
        } else {
            r = m;
        }
    }
    return l;
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n = 0;
    cin >> n;
    for (int i = 0; i < n; i++){
        cin >> a[i];
    }
    sort(0,n);
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int l,r;
        cin >> l >> r;
        int x = binaryLeft(l-1, -1, n);
        int y = binaryRight(r+1, -1, n);
        cout << y-x+1 << " ";
    }
    return 0;
}

 
