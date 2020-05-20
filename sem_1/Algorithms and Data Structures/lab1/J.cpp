#include <bits/stdc++.h>
using  namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int v1,v2;
    double  a;
    cin >> v1 >> v2 >> a;
    double a1 = 1-a;
    double l = 0;
    double r = 1;
    for (int i = 0; i < 100; i++) {
        double m1 =(l+r)/2;
        double m2 = m1+m1/10000000;
        double f1 = sqrt(a1*a1+m1*m1)/v1 + sqrt(a*a+(1-m1)*(1-m1))/v2;
        double f2 = sqrt(a1*a1+m2*m2)/v1 + sqrt(a*a+(1-m2)*(1-m2))/v2;
        if (f1<f2) {
            r = m2;
        }
        else {
            l = m1;
        }
    }
    cout << (l+r)/2;
    return 0;
} 
