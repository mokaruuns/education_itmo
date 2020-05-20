#include <bits/stdc++.h>

#define ENDL "\n"
typedef long long LL;
using namespace std;

array<array<int, 4>, 4000000> t;
/*
 * t[][0] - самый левый на отрезке - есть ли что-то слева
 * t[][1] - самый правый на отрезке - есть ли что-то справа
 * t[][2] - длина отрезков
 * t[][3] - количество отрезков
*/
vector<int> tSet(4000000, -1);

void propagate(int pos, int left_pos, int right_pos) {
    if (tSet[pos] != -1) {
        if (tSet[pos] == 0) {
            t[pos][0] = 0;
            t[pos][1] = 0;
            t[pos][2] = 0;
            t[pos][3] = 0;
        } else {
            t[pos][0] = 1;
            t[pos][1] = 1;
            t[pos][2] = right_pos - left_pos + 1;
            t[pos][3] = 1;
        }
        if (right_pos != left_pos) {
            tSet[2 * pos + 1] = tSet[pos];
            tSet[2 * pos + 2] = tSet[pos];
        }
        tSet[pos] = -1;
    }
}

void update(int pos, int left_pos, int right_pos, int left, int right, int value) {
    propagate(pos, left_pos, right_pos);
    if (left > right_pos || right < left_pos) {
        return;
    }
    if (left_pos >= left && right_pos <= right) {
        tSet[pos] = value;
        propagate(pos, left_pos, right_pos);
        return;
    }
    int middle = (left_pos + right_pos) / 2;
    update(2 * pos + 1, left_pos, middle, left, right, value);
    update(2 * pos + 2, middle + 1, right_pos, left, right, value);
    // присвоить текущему значению пересчет по 4 значениям смотря в детей в t[pos]
    t[pos][0] = t[2 * pos + 1][0];
    t[pos][1] = t[2 * pos + 2][1];
    if (t[2 * pos + 2][0] == 1 && t[2 * pos + 1][1] == 1) {
        t[pos][3] = t[2 * pos + 2][3] + t[2 * pos + 1][3] - 1;
    } else {
        t[pos][3] = t[2 * pos + 2][3] + t[2 * pos + 1][3];
    }
    t[pos][2] = t[2 * pos + 1][2] + t[2 * pos + 2][2];
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
//    freopen("parking.in", "r", stdin);
//    freopen("parking.out", "w", stdout);
    int shift = (1 << (int) ceil(log2(1000000))) - 1;
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        string s;
        int l, r;
        cin >> s >> l >> r;
        l += shift+500001;
        if (s == "B") {
            update(0, shift, 2 * shift, l - 1, l + r - 2, 1);
        } else {
            update(0, shift, 2 * shift, l - 1, l + r - 2, 0);
        }
        cout << t[0][3] << " " << t[0][2] << ENDL;
    }
}

 
