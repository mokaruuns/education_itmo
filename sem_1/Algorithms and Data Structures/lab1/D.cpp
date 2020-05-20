#include <bits/stdc++.h>

using namespace std;


void insert(vector <int> &heap, int b){
    heap.push_back(b);
    int v = heap.size()-1;
    while (v>0 && heap[(v-1)/2]<heap[v]) {
        swap(heap[v],heap[(v-1)/2]);
        v = (v-1)/2;
    }
}

void extract(vector <int> &heap) {
    heap[0] = heap[heap.size()-1];
    heap.pop_back();
    int i = 0;
    while (true){
        int j = i;
        if (2*i+1 < heap.size() && heap[2*i+1]>heap[j]){
            j = 2*i+1;
        }
        if (2*i+2 < heap.size() && heap[2*i+2]>heap[j]){
            j = 2*i+2;
        }
        if (j==i){
            break;
        }
        swap(heap[i],heap[j]);
        i = j;
    }
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vector <int> heap;
    int n;
    cin >> n;
    int a,b;
    for (int i = 0; i < n; i++) {
        cin >> a;
        if (a==1){
            cout << heap[0] << "\n";
            extract(heap);
        }
        else {
            cin >> b;
            insert(heap, b);
        }
    }
    return 0;
}

 
