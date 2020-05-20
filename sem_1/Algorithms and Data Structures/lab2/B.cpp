#include <bits/stdc++.h>

using namespace std;
int a[1000000];
int position = 0;
int k = 1;
int ans = 0;

void remove() {
    a[position-1] = 0;
    position--;
}


void add(int x) {
    if (position > 0 && a[position-1] == x) {
        k++;
    } else {
        if (k>=3) {
            for (int i = 0; i < k; i++) {
                remove();
            }
            ans += k;
            if (a[position-1]==x){
                if (a[position-2]==x) {
                    k = 3;
                }
                else {
                    k = 2;
                }
            }
            else {
                k = 1;
            }
        } else { k = 1;}
    }
    a[position] = x;
    position++;
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        add(x);
    }
    add(10);
    cout << ans;
    return 0;
} 
