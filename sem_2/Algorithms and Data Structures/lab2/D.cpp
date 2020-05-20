#include <bits/stdc++.h>
using namespace std;


struct Node {
    Node *left;
    Node *right;
    long long x;
    long long y;
    long long c;
    long long sum;
};
struct Pair {
    Node *first;
    Node *second;
};

Node *root = nullptr;

Node *createNode(long long x) {
    Node *node = new Node;
    node->right = nullptr;
    node->left = nullptr;
    node->x = x;
    node->y = rand();
    node->c = 1;
    node->sum = x;
    return node;
}

long long sizeOf(Node *A) {
    return !A ? 0 : A->c;
}

long long sumOf(Node *A) {
    return !A ? 0 : A->sum;
}

void update(Node *A) {
    A->c = sizeOf(A->left) + sizeOf(A->right) + 1;
    A->sum = sumOf(A->left) + sumOf(A->right) + A->x;
}

Node *merge(Node *A, Node *B) {
    if (!A) return B;
    if (!B) return A;
    if (A->y > B->y) {
        A->right = merge(A->right, B);
        update(A);
        return A;
    } else {
        B->left = merge(A, B->left);
        update(B);
        return B;
    }
}

Pair *split(Node *A, long long x) {
    if (!A) return new Pair{nullptr, nullptr};
    if (A->x < x) {
        Pair *tmp = split(A->right, x);
        A->right = tmp->first;
        update(A);
        return new Pair{A, tmp->second};
    } else {
        Pair *tmp = split(A->left, x);
        A->left = tmp->second;
        update(A);
        return new Pair{tmp->first, A};
    }
}

Node *insert(Node *A, long long x) {
    Pair *tmp = split(A, x);
    Node *node = createNode(x);
    Node *merge_left = merge(tmp->first, node);
    Node *merge_right = merge(merge_left, tmp->second);
    return merge_right;
}

Node *remote(Node *A, long long x) {
    Pair *tmp = split(A, x);
    Pair *tmp2 = split(tmp->second, x + 1);
    return merge(tmp->first, tmp2->second);
}

long long returnNodeMax(Node *A, long long pos) {
    Node *cur = A;
    while (cur) {
        long long sizeLeft = sizeOf(cur->left);
        if (sizeLeft == pos) {
            return cur->x;
        }
        cur = sizeLeft > pos ? cur->left : cur->right;
        if (sizeLeft < pos) {
            pos -= sizeLeft + 1;
        }
    }
    return 0;
}

long long getSum(Node *A, long long l, long long r) {
    Pair *treeOne = split(A, l);
    Pair *treeTwo = split(treeOne->second, r);
    long long res;
    if (treeTwo->first) {
        res = treeTwo->first->sum;
    } else {
        res = 0;
    }
    A = merge(treeOne->first, merge(treeTwo->first, treeTwo->second));
    return res;

}

set<long long> si;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    long long n;
    cin >> n;
    long long s_prev = 0;
    for (long long i = 0; i < n; i++) {
        char op;
        cin >> op;
        if (op == '+') {
            long long value;
            cin >> value;
            value = (value + s_prev) % 1000000000;
            if (!si.count(value)) {
                root = insert(root, value);
                si.insert(value);
            }
            s_prev = 0;
        } else if (op == '?') {
            long long left, right;
            cin >> left >> right;
            s_prev = getSum(root, left, right + 1);
            cout << s_prev << endl;
        }
    }
    return 0;
} 
