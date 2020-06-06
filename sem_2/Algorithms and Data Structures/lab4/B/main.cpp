#include <bits/stdc++.h>

using namespace std;

char* in = "cobbler.in";
char* out = "cobbler.out";


int main() {
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    int k, n;
    vector<int> vi;
    cin >> k >> n;
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        if (tmp <= k) {
            vi.push_back(tmp);
        }
    }
    sort(vi.begin(), vi.end());
    int s = 0;
    int l = 0;
    for ( auto i: vi) {
        if (s + i <= k){
            s+=i;
            l++;
        } else {
            break;
        }
    }
    cout << l;
    return 0;
}