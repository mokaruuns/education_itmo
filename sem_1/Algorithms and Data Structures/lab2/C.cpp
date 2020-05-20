#include <bits/stdc++.h>
#define endl "\n"
using namespace std;

int q[100000];
int t[100000];
int first = 0;
int last = 0;

void add(int x) {
    q[last] = x;
    t[x]=last;
    last = (last + 1) % 100000;
}

void removeFirst() {
    q[first] = 0;
    first = (first + 1) % 100000;
}

void removeLast() {
    q[last] = 0;
    if (last == 0) {
        last = 100000;
    }
    last--;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n;
    cin >> n;
    int ans;
    for (int i = 0; i < n; i++) {
        int a;
        cin >> a;
        int id;
        switch (a) {
            case 1:
                cin >> id;
                add(id);
                break;
            case 2:
                removeFirst();
                break;
            case 3:
                removeLast();
                break;
            case 4:
                cin >> id;
                ans = t[id];
                if (first<=last){
                    cout << ans-first << endl;
                }
                else {
                    if (id < first) {
                        cout << 100000-first + id-1 << endl;
                    }
                    else {
                        cout << id - first -1 << endl;
                    }
                }

                //подсчет перед ним
                break;
            case 5:
                cout << q[first] << endl;
                break;
        }
    }
    return 0;
} 
