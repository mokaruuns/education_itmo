#include <bits/stdc++.h>

using namespace std;

char *in = "request.in";
char *out = "request.out";

bool comp(pair<int, int> A, pair<int, int> B)
{
    if (A.second == B.second)
    {
        return A.first < B.first;
    }
    else
    {
        return A.second < B.second;
    }
}

int main()
{
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    int n;
    vector<pair<int, int>> vpii;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int s, f;
        cin >> s >> f;
        vpii.push_back(make_pair(s, f));
    }
    sort(vpii.begin(), vpii.end(), comp);
    int ps = 0;
    int k = 0;
    for (auto i : vpii)
    {
        if (i.first >= ps){
            ps = i.second;
            k++;
        }
    }
    cout << k;
    return 0;
}
