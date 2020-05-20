#include <bits/stdc++.h>

using namespace std;
struct Key {
    int x;
    int y;
};

struct Node {
    Node *left;
    Node *right;
    struct Key *key;
};
struct Pair {
    Node *first;
    Node *second;
};

Node *root = nullptr;

Node *createNode(struct Key *key) {
    Node *node = new Node;
    node->key = key;
    node->right = nullptr;
    node->left = nullptr;
    return node;
}

Node *merge(Node *A, Node *B) {
    if (!A) return B;
    if (!B) return A;
    if (A->key->y > B->key->y) {
        A->right = merge(A->right, B);
        return A;
    } else {
        B->left = merge(A, B->left);
        return B;
    }
}

Pair *split(Node *A, int x) {// возвращеет правое и левое поддерево после разделения
    if (!A) {
        return new Pair{nullptr, nullptr};
    }
    if (A->key->x < x) {
        Pair *tmp = split(A->right, x);
        A->right = tmp->first;
        return new Pair{A, tmp->second};
    } else {
        Pair *tmp = split(A->left, x);
        A->left = tmp->second;
        return new Pair{tmp->first, A};
    }
}

Node *returnNode(int x, Node *node) {
    if (node) {
        if (x == node->key->x) {
            return node;
        } else {
            if (x < node->key->x) {
                return returnNode(x, node->left);
            } else {
                return returnNode(x, node->right);
            }
        }
    } else {
        return nullptr;
    }
}

Node *insert(Node *A, Node *node) {
    Pair *Tp = split(A, node->key->x);
    Node *Tn1 = merge(Tp->first, node);
    Node *Tn2 = merge(Tn1, Tp->second);
    return Tn2;
}

Node *remove(Node *root, int x) {
    Pair *A = split(root, x);
    Pair *B = split(A->second, x + 1);
    if (B->first) {
        delete B->first;
    }
    return merge(A->first, B->second);
}


Node *nextNode(int key) {
    Node *current = root;
    Node *successor = nullptr;
    while (current) {
        if (current->key->x > key) {
            successor = current;
            current = current->left;
        } else {
            current = current->right;
        }
    }
    return successor;
}

Node *prevNode(int key) {
    Node *current = root;
    Node *successor = nullptr;
    while (current) {
        if (current->key->x < key) {
            successor = current;
            current = current->right;
        } else {
            current = current->left;
        }
    }
    return successor;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    string s;
    int value;
    Node *node = nullptr;
    Key *key = nullptr;
    Node *Tn;
    Node *Td;
    while (cin >> s >> value) {
        switch (s[0]) {
            case 'i':
                key = new Key{value, rand()};
                node = createNode(key);
                root = insert(root, node);
                break;
            case 'e':
                cout << (returnNode(value, root) ? "true" : "false") << endl;
                break;
            case 'd':
                root = remove(root, value);
                break;
            case 'n':
                node = nextNode(value);
                if (node) cout << node->key->x << endl;
                else cout << "none" << endl;
                break;
            case 'p':
                node = prevNode(value);
                if (node) cout << node->key->x << endl;
                else cout << "none" << endl;
                break;
        }
    }
    return 0;
} 
