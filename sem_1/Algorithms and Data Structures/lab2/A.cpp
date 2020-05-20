#include <bits/stdc++.h>
using namespace std;
int a[1000000];
int minimum[1000000];
int k = 0;
void add(int x) {
    if (k==0) {
        minimum[k]=x;
    }
    else {
        minimum[k] = min(minimum[k-1],x);
    }
    a[k] = x;
    k++;
}

void remove() {
    k--;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int t, x;
        cin >> t;
        if (t==1) {
            cin >> x;
            add(x);
        } else {
            if (t==2) {
                remove();
            }
            else {
                cout << minimum[k-1] << "\n";
            }
        }
    }
    return 0;
} 
