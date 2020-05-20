#include <bits/stdc++.h>

#define ENDL "\n";
typedef long long LL;
using namespace std;

int a[200][200][200];

int p(int x) {
    return (x | (x + 1));
}

int f(int x) {
    return (x & (x + 1)) - 1;
}

void update(int n) {
    int x, y, z, k;
    cin >> x >> y >> z >> k;
    for (int i = x; i < n; i = p(i))
        for (int j = y; j < n; j = p(j))
            for (int w = z; w < n; w = p(w))
                a[i][j][w] += k;
}


int get_sum(int x, int y, int z) {
    int res = 0;
    for (int i = x; i >= 0; i = f(i))
        for (int j = y; j >= 0; j = f(j))
            for (int w = z; w >= 0; w = f(w))
                res += a[i][j][w];
    return res;
}

int delete_more(int x1, int y1, int z1, int x2, int y2, int z2) {
    return get_sum(x2, y2, z2)
           + get_sum(x1 - 1, y1 - 1, z2)
           + get_sum(x1 - 1, y2, z1 - 1)
           + get_sum(x2, y1 - 1, z1 - 1)
           - get_sum(x1 - 1, y2, z2)
           - get_sum(x2, y1 - 1, z2)
           - get_sum(x2, y2, z1 - 1)
           - get_sum(x1 - 1, y1 - 1, z1 - 1);
}

int main() {
    int n;
    cin >> n;
    int m;
    cin >> m;
    while (m != 3) {
        if (m == 1) {
            update(n);
        } else {
            int x1, y1, z1, x2, y2, z2;
            cin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;
            cout << delete_more(x1, y1, z1, x2, y2, z2) << ENDL;
        }
        cin >> m;
    }
    return 0;
}
 
