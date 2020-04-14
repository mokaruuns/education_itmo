#include <bits/stdc++.h>

using namespace std;

struct Node {
    Node *left;
    Node *right;
    int key;
};
Node *root = nullptr;

Node *createNode(int key) {
    Node *n = new Node;
    n->key = key;
    n->right = nullptr;
    n->left = nullptr;
    return n;
}

void addNode(int key, Node *node) {
    if (root == nullptr) {
        root = createNode(key);
    } else if (key < node->key) {
        if (node->left != nullptr) {
            addNode(key, node->left);
        } else {
            node->left = createNode(key);
        }
    } else if (key > node->key) {
        if (node->right != nullptr) {
            addNode(key, node->right);
        } else {
            node->right = createNode(key);
        }
    }
}

void printInOrder(Node *node) {
    if (root != nullptr) {
        if (node->left != nullptr) {
            printInOrder(node->left);
        }
        cout << node->key << " ";
        if (node->right != nullptr) {
            printInOrder(node->right);
        }
    }
}

Node *returnNode(int key, Node *node) {
    if (node != nullptr) {
        if (key == node->key) {
            return node;
        } else {
            if (key < node->key) {
                return returnNode(key, node->left);
            } else {
                return returnNode(key, node->right);
            }
        }
    } else {
        return nullptr;
    }
}

int main() {
    string s;
    int value;
    while (cin >> s >> value) {
        switch (s[0]) {
            case 'i':
                addNode(value, root);
                break;
            case 'p':
                printInOrder(root);
                break;
        }
    }
    return 0;
}
