#include <bits/stdc++.h>

using namespace std;
int act[101];
vector<map<int, vector<int>>> vmiv(101);
set<int> qset;
int main(){
    freopen("problem2.in", "r", stdin);
    freopen("problem2.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    string s;
    int n,m,k;
    cin >> s;
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++){
        int tmp;
        cin >> tmp;
        act[tmp] = 1;
    }
    qset.insert(1);
    for (int i = 0; i < m; i++){
        int a,b;
        char c;
        cin >> a >> b >> c;
        if (vmiv[a].count(c)!=0){
            vmiv[a][c].push_back(b);
        } else {
            vmiv[a][c].push_back(b);
        }
    }
    for (int i = 0; i < s.size(); i++){
        set<int> qset2;
        int cur;
        while (!qset.empty()){
            cur = *qset.begin();
            qset.erase(cur);
            if (vmiv[cur].count(s[i])){
                for (int j = 0; j < vmiv[cur][s[i]].size(); j++){
                    qset2.insert(vmiv[cur][s[i]][j]);
                }
            }
        }
        qset = qset2;
    }
    for(auto i : qset){
        if (act[i]==1){
            cout << "Accepts";
            return 0;
        }
    }
    cout << "Rejects";
}
