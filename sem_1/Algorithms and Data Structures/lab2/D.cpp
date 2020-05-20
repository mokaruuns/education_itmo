#include <bits/stdc++.h>
#define endl "\n"
using namespace std;

int q1[1000000];
int q2[1000000];
int first1 = 0;
int first2 = 0;
int last1 = 0;
int last2 = 0;
int k = 0;
int len1 = 0;
int len2 = 0;

void add_2(int x) {
    len2++;
    q2[last2] = x;
    last2 = (last2 + 1) % 1000000;
}
void addFirst_2(int x) {
    len2++;
    first2 = (first2 + 1000000 - 1)%1000000;
    q2[first2] = x;
}
void add_1(int x) {
    len1++;
    q1[last1] = x;
    last1 = (last1 + 1) % 1000000;
}

int removeFirst_2() {
    len2--;
    int ans = 0;
    ans = q2[first2];
    q2[first2] = 0;
    first2 = (first2 + 1) % 1000000;
    return ans;
}

int removeFirst_1() {
    len1--;
    int ans = 0;
    ans = q1[first1];
    q1[first1] = 0;
    first1 = (first1 + 1) % 1000000;
    return ans;
}
void q2_move_to_q1() {
    add_1(removeFirst_2());
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        char a;
        cin >> a;
        int id;
        switch (a) {
            case '+':
                cin >> id;
                add_2(id);
                while(len1<len2){
                    q2_move_to_q1();
                }
                break;
            case '*':
                cin >> id;
                if (len1==len2){
                    add_1(id);
                } else {
                    addFirst_2(id);
                }
                break;
            case '-':
                cout << removeFirst_1() << endl;
                while(len1<len2){
                    q2_move_to_q1();
                }
                break;
        }
    }

    return 0;
} 
