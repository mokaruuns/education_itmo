#include <bits/stdc++.h>

using namespace std;

int COUNT_MAX = 280000;

struct Node {
    Node *left;
    Node *right;
    int value;
    int size;
    int y;
};
struct Pair {
    Node *first;
    Node *second;
};

Node *root = nullptr;


Node *createNode(int value) {
    Node *node = new Node;
    node->right = nullptr;
    node->left = nullptr;
    node->value = value;
    node->y = rand();
    node->size = 1;
    return node;
}

int sizeOf(Node *A) {
    return !A ? 0 : A->size;
}

void update(Node *A) {
    A->size = sizeOf(A->left) + sizeOf(A->right) + 1;
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

Pair *split(Node *A, int pos) {
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
    Pair *tmp = split(A, pos);
    Node *node = createNode(x);
    Node *merge_left = merge(tmp->first, node);
    Node *merge_right = merge(merge_left, tmp->second);
    return merge_right;
}

Node *remote(Node *A, int pos) {
    Pair *tmp = split(A, pos);
    Pair *tmp2 = split(tmp->second, 1);
    return merge(tmp->first, tmp2->second);
}

vector<int> vi;

void printInOrder(Node *node) {
    if (root) {
        if (node->left) {
            printInOrder(node->left);
        }
//        vi.push_back(node->value);
        cout << node->value << " ";
        if (node->right) {
            printInOrder(node->right);
        }
    }
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        root = insert(root, i, tmp);
    }
    for (int i = 0; i < m; i++) {
        string s;
        int pos;
        cin >> s >> pos;
        if (s=="del"){
            root = remote(root, pos-1);
        } else {
            int value;
            cin >> value;
            root = insert(root, pos, value);
        }
    }
    cout << sizeOf(root) << endl;
    printInOrder(root);
    return 0;
} 
