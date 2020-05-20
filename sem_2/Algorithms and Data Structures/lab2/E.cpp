#include <bits/stdc++.h>

using namespace std;


struct Node {
    Node *left;
    Node *right;
    int x;
    int y;
    int c;
};
struct Pair {
    Node *first;
    Node *second;
};

Node *root = nullptr;

Node *createNode(int x) {
    Node *node = new Node;
    node->right = nullptr;
    node->left = nullptr;
    node->x = x;
    node->y = rand();
    node->c = 1;
    return node;
}

int sizeOf(Node *A) {
    return !A ? 0 : A->c;
}

void update(Node *A) {
    A->c = sizeOf(A->left) + sizeOf(A->right) + 1;
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

Pair *split(Node *A, int x) {
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

Node *insert(Node *A, int x) {
    Pair *tmp = split(A, x);
    Node *node = createNode(x);
    Node *merge_left = merge(tmp->first, node);
    Node *merge_right = merge(merge_left, tmp->second);
    return merge_right;
}

Node *remote(Node *A, int x) {
    Pair *tmp = split(A, x);
    Pair *tmp2 = split(tmp->second, x + 1);
    return merge(tmp->first, tmp2->second);
}

int returnNodeMax(Node *A, int pos) {
    Node *cur = A;
    while (cur) {
        int sizeLeft = sizeOf(cur->left);
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

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int op, value;
        cin >> op >> value;
        if (op == 1) {
            root = insert(root, value);
        } else if (op == 0) {
            int k = sizeOf(root) - abs(value);
            cout << returnNodeMax(root, k) << "\n";
        } else if (op == -1) {
            root = remote(root, value);
        }
    }
    return 0;
}
 
