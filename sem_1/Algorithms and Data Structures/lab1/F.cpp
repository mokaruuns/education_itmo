#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll binary_search(vector<int> &vi, ll l, ll r, ll val){
    ll middle;
    for (int i = 0; i < 50; i++){
        middle = (l+r)/2;
        if (val>vi[middle]){
            l = middle;
        }
        if (val<vi[middle]) {
            r = middle;
        }
    }
    if (vi[middle]==val){
        return middle;
    }
    if (middle+1<vi.size()){
        if(abs(vi[middle]-val)>abs(vi[middle+1]-val)){
            return middle + 1;
        }
    }
    return middle;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, m;
    cin >> n >> m;
    vector<int> vi;
    for (int i = 0; i < n; i++) {
        int t;
        cin >> t;
        vi.push_back(t);
    }
    for (int i = 0; i < m; i++) {
        int t;
        cin >> t;
        cout << vi[binary_search(vi, 0, n-1,t)] << "\n";
    }
    return 0;
}

 
