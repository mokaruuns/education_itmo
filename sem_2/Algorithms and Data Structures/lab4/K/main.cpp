#include <bits/stdc++.h>

#define fori(start, finish) for (ll i = start; i < finish; i++)
#define forj(start, finish) for (ll j = start; j < finish; j++)
#define forn(finish) for (ll i = 0; i < finish; i++)
typedef long long ll;

using namespace std;
pair<ll, ll> arr[1000000];

void open_file_rw() {
    char *in = "dowry.in";
    char *out = "dowry.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
}

struct Info {
    ll first;
    ll second;
    ll number;
};
vector<struct Info> get_vectors(vector<pair<ll, ll>> vi) {
    // first - weight, second - cost
    ll n = vi.size();
    vector<struct Info> result(1 << n, {0, 0, 0});
    forn(1 << n) {
        for (ll b = 0; b < n; b++) {
            if ((i >> b) & 1) {
                result[i].first += vi[b].first;
                result[i].second += vi[b].second;
                result[i].number |= (1<<b);
            }
        }
    }
    return result;
}

void get_numbers(ll left, ll right, vector<pair<ll, ll>> v1, vector<pair<ll, ll>> v2) {
    for (ll i = 0; i < v1.size(); i++){
        if ((left >> i) & 1) {
            cout << i+1 << " ";
        }
    }
    for (ll i = 0; i < v2.size(); i++){
        if ((right >> i) & 1) {
            cout << i+1+v1.size()<< " ";
        }
    }
}

bool comp(struct Info a, struct Info b) { return a.first < b.first; }

void build(ll l, ll r) {
    if (l != 0) {
        for (ll i = l; i < r - 1; i = i + 2) {
            // arr[i / 2] = arr[i] + arr[i + 1];
            if (arr[i].first > arr[i+1].second){
                arr[i / 2] = arr[i];
            } else {
                arr[i / 2] = arr[i + 1];
            }
        }
        build(l / 2, r / 2);
    }
}

pair<ll, ll> maximum(ll position, ll left_now, ll right_now, ll a,
                      ll b) { // [a,b] границы нашего массива
    if (a > right_now || b < left_now)
        return make_pair(0, 0);
    if (left_now >= a && right_now <= b) {
        return arr[position];
    }
    ll middle = (left_now + right_now) / 2;
    // return sum(2 * position + 1, left_now, middle, a, b) +
    //        sum(2 * position + 2, middle + 1, right_now, a, b);
    return max(maximum(2 * position + 1, left_now, middle, a, b),
               maximum(2 * position + 2, middle + 1, right_now, a, b));
}

int main() {
    open_file_rw();
    ll n;
    ll L, R;
    cin >> n >> L >> R;
    vector<pair<ll, ll>> vi1(n / 2);
    vector<pair<ll, ll>> vi2(n - n / 2);
    // first - weight, second - cost
    // считывание
    forn(n / 2) { cin >> vi1[i].first >> vi1[i].second; }
    forn(n - n / 2) { cin >> vi2[i].first >> vi2[i].second; }
    // получение множеств в двух половинах
    vector<struct Info> v1 = get_vectors(vi1);
    vector<struct Info> v2 = get_vectors(vi2);
    // сортировка второй части по весам
    sort(v2.begin(), v2.end(), comp);

    map<ll, set<ll>> index; // вес - индексы
    vector<ll> only_weight_2;
    for (ll i = 0; i < v2.size(); i++) { // сможем находить индекс по весу
        index[v2[i].first].insert(i);
        only_weight_2.push_back(v2[i].first);
    }
    ll z = v2.size();
    ll p = ceil(log2(z));
    for (ll i = 0; i < z; i++) {
        arr[i + (1 << p) - 1] = make_pair(v2[i].second, v2[i].number);
    }
    build((1 << p) - 1, (1 << (p + 1)));
    ll max_sum = 0;
    ll number_left = 0;
    ll number_right = 0;
    for (auto v : v1) {
        pair<ll, ll> pair_f = make_pair(0, 0);
        // получение индексов для нахождаения максимума
        ll v1_w = v.first;
        ll min_S1_w = L - v1_w;
        ll max_S1_w = R - v1_w;
        ll min_lb =
                *lower_bound(only_weight_2.begin(), only_weight_2.end(), min_S1_w); // ok

        ll max_ub =
                *(--upper_bound(only_weight_2.begin(), only_weight_2.end(), max_S1_w));
        if (max_S1_w >= 0 && max_ub>=L){
            // тут баги
            ll index_L = *(index[min_lb].begin());
            ll index_R = *(index[max_ub].rbegin());
            // ---
            // нахождения максимума на отрезке
            pair_f = maximum(0, 0, (1 << p) - 1, index_L, index_R);
        }
        if ((v.first < L && pair_f.second==0) || (v.first > R)){
            continue;
        }
        ll tmp_sum =  v.second + pair_f.first;
        if (tmp_sum > max_sum) {
            max_sum = tmp_sum;
            number_left = v.number;
            number_right = pair_f.second;
        }
    }
//    cout << max_sum << " " << number_left << " " << number_right;
    ll res = (number_left<<16)+(ll)number_right;
    cout << __builtin_popcount(res) << "\n";
    get_numbers(number_left, number_right, vi1, vi2);
}