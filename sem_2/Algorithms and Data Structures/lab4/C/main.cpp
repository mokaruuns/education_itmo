#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forn(finish) for (int i = 0; i < finish; i++)

using namespace std;

void open_file_rw() {
  char *in = "printing.in";
  char *out = "printing.out";
  freopen(in, "r", stdin);
  freopen(out, "w", stdout);
}

struct List {
  int lists;
  int cost;
  double w_cost;
};

bool comp(List A, List B) { return A.w_cost < B.w_cost; }

int main() {
  open_file_rw();
  vector<List> vL;
  int n;
  cin >> n;
  forn(7) {
    int cost;
    cin >> cost;
    int count = pow(10, i);
    vL.push_back({count, (int)cost, (double) cost / count});
  }
  sort(vL.begin(), vL.end(), comp);
  vector<List> vL2;
  vL2.push_back(vL.front());
  for (auto i : vL) {
    if (i.w_cost > vL2.back().w_cost) {
      vL2.push_back(i);
    }
  }
  int i = 0;
  int min_sum = INT32_MAX;
  while (i < vL2.size()) {
    if (vL2[i].lists >= n) {
      min_sum = min(min_sum, vL2[i].cost);
      i++;
    } else {
      int count = 0;
      int min_s = 0;
      while (i < vL2.size() && count < n) {
        int x = (n - count) / vL2[i].lists;
        count += vL2[i].lists * x;
        min_s += vL2[i].cost * x;
        i++;
      }
      if (count >= n) {
        min_sum = min(min_sum, min_s);
      }
    }
  }
  cout << min_sum;
}