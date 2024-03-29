#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;

using namespace std;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

const int alpha = 256;

vector<int> build_lcp(const string &s, const vector<int> &suff) {
    int n = s.length();
    vector<int> lcp(n);
    vector<int> position(n);
    for (int i = 0; i < n; i++) {
        position[suff[i]] = i;
    }
    int k = 0;
    for (int i = 0; i < n; i++) {
        if (k > 0) k--;
        if (position[i] == n - 1) {
            lcp[n - 1] = -1;
            k = 0;
        } else {
            int j = suff[position[i] + 1];
            while (max(i + k, j + k) < n && s[i + k] == s[j + k]) k++;
            lcp[position[i]] = k;
        }
    }
    return lcp;
}


vector<int> build_suff_mas(string basicString) {
    int n = basicString.length() + 1;
    int k = max(n, alpha);
    vector<int> chars(n);
    vector<int> cnt(alpha);
    vector<int> count(k);
    vector<int> count_copy(k);
    vector<int> suff(n);
    vector<int> suff_copy(n);
    vector<int> classes(n);
    FOR(i, 0, n) chars[i] = basicString[i];
    FOR(i, 0, n) cnt[chars[i]]++;
    FOR(i, 1, alpha) {
        count[i] = count[i - 1] + cnt[i - 1];
        count_copy[i] = count[i];
    }
    FOR(i, 0, n) suff[count_copy[chars[i]]++] = i;
    for (int l = 1; l < n; l <<= 1) {
        FOR(i, 0, n) {
            int id = (suff[i] - l + n) % n;
            suff_copy[count[chars[id]]++] = id;
        }
        int size = 0;
        FOR(i, 0, n) {
            if (i == 0 || chars[suff_copy[i]] != chars[suff_copy[i - 1]] ||
                chars[(suff_copy[i] + l) % n] != chars[(suff_copy[i - 1] + l) % n]) {
                count[size++] = i;
            }
            classes[suff_copy[i]] = size - 1;
        }
        suff = suff_copy;
        chars = classes;
    }
    return suff;
}

void elementary_search(string s){

}

void solve() {
    vector<string> ss;
    int n;
    cin >> n;
    FOR(i, 0, n) {
        string str;
        cin >> str;
        ss.push_back(str);
    }
    string basicString;
    cin >> basicString;
    vector<int> suff = build_suff_mas(basicString);
    FOR(i, 1, basicString.length() + 1) cout << suff[i] + 1 << " ";

}

int main() {
    fastIO();
    solve();
    return 0;
}
