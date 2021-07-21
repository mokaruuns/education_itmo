#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (ll variable = begin; variable < end; variable++)
typedef long long ll;
using namespace std;


void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("destroy.in", "r", stdin);
    freopen("destroy.out", "w", stdout);
}

struct Edge {
    ll from;
    ll to;
    ll weight;
    ll number;
};

vector<Edge> g;
ll parent[100001];

ll get_root(ll v) {
    if (parent[v] < 0) return v;
    else {
        parent[v] = get_root(parent[v]);
        return parent[v];
    }
}

bool join(ll left, ll right) {
    left = get_root(left);
    right = get_root(right);
    if (left == right) return false;
    if (left > right) swap(left, right);
    parent[left] += parent[right];
    parent[right] = left;
    return true;
}

bool comp(Edge left, Edge right) {
    return left.weight > right.weight;
}

bool comp2(Edge left, Edge right) {
    return left.weight < right.weight;
}

void solve() {
    ll n, m, s;
    cin >> n >> m >> s;
    memset(parent, -1, sizeof(parent));
    FOR(i, 0, m) {
        ll v, u, w;
        cin >> v >> u >> w;
        g.push_back({v, u, w, i + 1});
    }
    sort(g.begin(), g.end(), comp);
    vector<Edge> answer;
    for (auto e: g) {
        if (join(e.from, e.to)) {
//            cout << e.from << " " << e.to << endl;
        } else {
//            cout << e.from << " " << e.to << endl;
            answer.push_back(e);
        }
    }
    sort(answer.begin(), answer.end(), comp2);
    ll ans_sum = 0;
    vector<ll> ans;
    for (Edge e: answer) {
        if (ans_sum + e.weight <= s) {
            ans_sum += e.weight;
            ans.push_back(e.number);
        } else {
            break;
        }
    }
    cout << ans.size() << endl;
    sort(ans.begin(), ans.end());
    for(ll i : ans) {
        cout << i << " ";
    }
}

int main() {
    fastIO();
    solve();
    return 0;
}