#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
    char *in = "apples.in";
    char *out = "apples.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}

struct Apples {
    int position;
    int start;
    int end;
};

bool comp1(Apples A, Apples B) {
    return A.start < B.start;
}
bool comp2(Apples A, Apples B) {
    return A.end > B.end;
}

int main() {
    open_file_rw();
    vector<Apples> vpii;
    vector<Apples> vpii_up;
    vector<Apples> vpii_down;
    int n, height;
    cin >> n >> height;
    forn(n) {
        int a, b;
        cin >> a >> b;
        if (b - a > 0) {
            vpii_up.push_back({i + 1, a, b});
        } else {
            vpii_down.push_back({i + 1, a, b});
        }
    }
    vector<int> ans;
    sort(vpii_up.begin(), vpii_up.end(), comp1);
    sort(vpii_down.begin(), vpii_down.end(), comp2);
    for (auto i : vpii_up) {
        if (height - i.start <= 0) {
            cout << -1;
            return 0;
        }
        height -= (i.start - i.end);
        ans.push_back(i.position);
    }
    for (auto i : vpii_down) {   
        if (height - i.start <= 0) {
            cout << -1;
            return 0;
        }
        height -= (i.start - i.end);
        ans.push_back(i.position);
    }
    for (auto i : ans) {
        cout << i << " ";
    }
}