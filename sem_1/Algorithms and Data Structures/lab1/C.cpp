#include <bits/stdc++.h>


using  namespace std;

long long cnt = 0;
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
            cnt += (n-i);
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



int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int n = 0;
    cin >> n;

    for (int i = 0; i < n; i++){
        cin >> a[i];
    }
    sort(0,n);
    cout << cnt;
    return 0;
} 
