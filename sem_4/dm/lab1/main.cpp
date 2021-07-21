#include <bits/stdc++.h>

#define endl "\n"
#define FOR(variable, begin, end) for (ll variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;

ll MOD = 1e12;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("test.in", "r", stdin);
    freopen("test.out", "w", stdout);
}

ll g;

vector<ll> sum(const vector<ll> &P, const vector<ll> &Q, ll new_size) {
    g = 0;
    vector<ll> result(new_size, 0);
    FOR(i, 0, new_size) {
        result[i] += (P[i] + Q[i]) % MOD;
        result[i] %= MOD;
        if (result[i] != 0) {
            g = i;
        }
    }
    return result;
}

vector<int> mult(const vector<int> &P, int pSize, const vector<int> &Q, int qSize, int new_size) {
    g = 0;
    vector<int> result(new_size, 0);
    FOR(i, 0, pSize) {
        FOR(j, 0, qSize) {
            result[i + j] += (P[i] * Q[j]);
//            result[i + j] %= MOD;
        }
    }
    FOR(i, 0, new_size) {
        if (result[i] != 0) {
            g = i;
        }
    }
    return result;
}


vector<ll> div(const vector<ll> &A, ll pSize, const vector<ll> &B, ll qSize) {
    vector<ll> C(1000, 0);
    C[0] = A[0] / B[0];
    FOR(i, 1, 1000) {
        ll s = 0;
        FOR(k, 0, i) {
            s += (C[k] * B[i - k]) % MOD;
            s %= MOD;
        }
        ll tmp = (A[i] - s) % MOD;
        C[i] = tmp / B[0];
    }
    return C;
}

void prll_result(const vector<int> &vector, ll size) {
    cout << size << endl;
    FOR(i, 0, size) {
        cout << vector[i] << " ";
    }
    cout << endl;
}

void prll_result(const vector<int> &vector) {
    for (ll c_i: vector) {
        if (c_i < 0) {
            cout << c_i + MOD << " ";
        } else {
            cout << c_i << " ";

        }
    }
    cout << endl;
}

vector<int> make_full_A(const vector<int> &A, const vector<int> &C) {
    vector<int> full_A;
    for (int i: A) {
        full_A.push_back(i);
    }
    int k = A.size();
    FOR(i, k, 1001) {
        int tmp_sum;
        FOR(j, 1, k) {
            tmp_sum += C[j] * full_A[i - j];
        }
        full_A.push_back(tmp_sum);
    }
    return full_A;
}

vector<int> make_Q(const vector<int> &C) {
    vector<int> Q;
    Q.push_back(1);
    for (int i: C) {
        Q.push_back(-i);
    }
    return Q;
}

void solve() {
    int k;
    cin >> k;
    vector<int> A(k, 0);
    vector<int> C(k, 0);
    FOR(i, 0, k) {
        cin >> A[i];
    }
    FOR(i, 0, k) {
        cin >> C[i];
    }
    vector<int> full_A = make_full_A(A, C);
    vector<int> Q = make_Q(C);
    vector<int> P = mult(full_A, full_A.size(), Q, Q.size(), full_A.size() + Q.size());
    cout << g << endl;
    for (int i: P) {
        cout << i << " ";
    }
    cout << endl <<  k << endl;
    for (int i: Q) {
        cout << i << " ";
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}