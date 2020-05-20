#include <bits/stdc++.h>


using  namespace std;

double c;

double binary_search(double left, double right) {
    double middle = (left+right)/2.0;
    double answer = middle*middle+sqrt(middle);
    if (abs(c-answer)<0.000001) {
        cout <<setprecision(7) <<  middle;
        exit(0);
    }
    if (answer>c){
        binary_search(left,middle);
    } else {
        binary_search(middle ,right);
    }
}

int main() {
    cin >> c;
    binary_search(0.5,1000000);
    return 0;
} 
