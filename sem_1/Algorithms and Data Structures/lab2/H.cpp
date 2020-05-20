#include <bits/stdc++.h>
#define endl "\n"
using namespace std;
int p[200001];
vector <vector<int>> w(200001);
int size_two[200001];

int find(int x) {
    if(p[x]!=x) {
        p[x] = find(p[x]);
    }
    return p[x];
}

void union_clan(int x, int y) {
    int x_p = find(x);
    int y_p = find(y);
    if (x_p != y_p){
        if (size_two[x_p] > size_two[y_p]){
            swap(x_p, y_p);
        }
        p[x_p] = y_p;
        for (int i : w[x_p]) {
            w[y_p].push_back(i);
        }
    }
}



int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n,m;
    cin >> n >> m;
    for (int i = 0; i < n + 1; i++) {
        p[i]=i;
        w[i].push_back(i);
    }
    for (int i = 0; i < m; i++) {
        string s;
        cin >> s;
        if (s == "join"){
                int a,b;
                cin >> a >> b;
                union_clan(a,b);
        }
        else{
            if (s == "get") {
                int a;
                cin >> a;
                int z = find(a);
                cout << size_two[a] << endl;
            }
            else {
                int a, b;
                cin >> a >> b;
                int z = find(a);
                for (int j: w[z]) {
                    size_two[j] += b;
                }
            }
        }
    }

    return 0;
} 
