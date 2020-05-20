#include <bits/stdc++.h>

using namespace std;


struct Node {
    Node *left;
    Node *right;
    int value;
    int size;
    int y;
    bool reversed;
};
struct Pair {
    Node *first;
    Node *second;
};

Node *root = nullptr;

void push(Node *A) {
    if (!A) return;
    if (!A->reversed) return;
    Node *temp = A->left;
    A->left = A->right;
    A->right = temp;
    A->reversed = false;
    if (A->left) {
        A->left->reversed ^= true;
    }
    if (A->right) {
        A->right->reversed ^= true;
    }
}


Node *createNode(int value) {
    Node *node = new Node;
    node->right = nullptr;
    node->left = nullptr;
    node->value = value;
    node->y = rand();
    node->size = 1;
    node->reversed = false;
    return node;
}

int sizeOf(Node *A) {
    push(A);
    return !A ? 0 : A->size;
}

void update(Node *A) {
    push(A);
    A->size = sizeOf(A->left) + sizeOf(A->right) + 1;
}

Node *merge(Node *A, Node *B) {
    push(A);
    push(B);
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

Pair *split(Node *A, int pos) {
    push(A);
    if (!A) return new Pair{nullptr, nullptr};
    if (sizeOf(A->left) < pos) {
        Pair *tmp = split(A->right, pos - sizeOf(A->left) - 1);
        A->right = tmp->first;
        update(A);
        return new Pair{A, tmp->second};
    } else {
        Pair *tmp = split(A->left, pos);
        A->left = tmp->second;
        update(A);
        return new Pair{tmp->first, A};
    }
}

Node *insert(Node *A, int pos, int x) {
    push(A);
    Pair *tmp = split(A, pos);
    Node *node = createNode(x);
    Node *merge_left = merge(tmp->first, node);
    Node *merge_right = merge(merge_left, tmp->second);
    return merge_right;
}

Node *remote(Node *A, int pos) {
    push(A);
    Pair *tmp = split(A, pos - 1);
    push(A);
    Pair *tmp2 = split(tmp->second, 1);
    push(A);
    return merge(tmp->first, tmp2->second);
}

void printInOrder(Node *node) {
    push(node);
    if (root) {
        if (node->left) {
            printInOrder(node->left);
        }
        cout << node->value << " ";
        if (node->right) {
            printInOrder(node->right);
        }
    }
}

Node *reverse(Node *A, int left, int right) {
    Pair *tmp1 = split(A, left - 1);
    Pair *tmp2 = split(tmp1->second, right - left + 1);
    tmp2->first->reversed ^= true;
    return merge(merge(tmp1->first, tmp2->first), tmp2->second);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        root = insert(root, i, i+1);
    }
    for (int i = 0; i < m; i++) {
        int left, right;
        cin >> left >> right;
        root = reverse(root, left, right);
    }
    printInOrder(root);
    return 0;
} 
