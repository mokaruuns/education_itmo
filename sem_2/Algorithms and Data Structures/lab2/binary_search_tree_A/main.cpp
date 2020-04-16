#include <bits/stdc++.h>

using namespace std;

struct Node {
    Node *left;
    Node *right;
    int key;
};

void deleteRoot();

void deleteNode(int key, Node *parent);

Node *root = nullptr;

Node *createNode(int key) {
    Node *node = new Node;
    node->key = key;
    node->right = nullptr;
    node->left = nullptr;
    return node;
}

void addNode(int key, Node *node) {
    if (!root) {
        root = createNode(key);
    } else if (key < node->key) {
        if (node->left) {
            addNode(key, node->left);
        } else {
            node->left = createNode(key);
        }
    } else if (key > node->key) {
        if (node->right) {
            addNode(key, node->right);
        } else {
            node->right = createNode(key);
        }
    }
}

void printInOrder(Node *node) {
    if (root) {
        if (node->left) {
            printInOrder(node->left);
        }
        cout << node->key << " ";
        if (node->right) {
            printInOrder(node->right);
        }
    }
}

Node *returnNode(int key, Node *node) {
    if (node) {
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

int findSmallest(Node *node) {
    if (!root) {
        return -1;
    } else {
        if (node->left) {
            return findSmallest(node->left);
        } else {
            return node->key;
        }
    }
}

int findBiggest(Node *node) {
    if (!root) {
        return -1;
    } else {
        if (node->right) {
            return findBiggest(node->right);
        } else {
            return node->key;
        }
    }
}

void deleteMatch(Node *parent, Node *match, bool left) {
    if (root) {
        Node *delNode;
        int smallestRightSubtree;
        if (!match->left && !match->right) {
            delNode = match;
            left ? parent->left = nullptr : parent->right = nullptr;
            delete delNode;
        } else if (!match->left && match->right) {
            left ? parent->left = match->right : parent->right = match->right;
            match->right = nullptr;
            delNode = match;
            delete delNode;
        } else if (match->left && !match->right) {
            left ? parent->left = match->left : parent->right = match->left;
            match->left = nullptr;
            delNode = match;
            delete delNode;
        } else {
            smallestRightSubtree = findSmallest(match->right);
            deleteNode(smallestRightSubtree, match);
            match->key = smallestRightSubtree;
        }
    }
}


void deleteNode(int key, Node *parent) {
    if (root) {
        if (root->key == key) {
            deleteRoot();
        } else {
            if (key < parent->key && parent->left) {
                parent->left->key == key ?
                deleteMatch(parent, parent->left, true) :
                deleteNode(key, parent->left);
            } else if (key > parent->key && parent->right) {
                parent->right->key == key ?
                deleteMatch(parent, parent->right, false) :
                deleteNode(key, parent->right);
            }
        }
    }
}


void deleteRoot() {
    if (root) {
        Node *delNode = root;
        int smallestRightSubtree;
        if (!root->left && !root->right) {
            root = nullptr;
            delete delNode;
        } else if (!root->left && root->right) {
            root = root->right;
            delNode->right = nullptr;
            delete delNode;
        } else if (root->left && !root->right) {
            root = root->left;
            delNode->left = nullptr;
            delete delNode;
        } else {
            smallestRightSubtree = findSmallest(root->right);
            deleteNode(smallestRightSubtree, root);
            root->key = smallestRightSubtree;
        }
    }
}

Node *nextNode(int key) {
    Node *current = root;
    Node *successor = nullptr;
    while (current) {
        if (current->key > key) {
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
        if (current->key < key) {
            successor = current;
            current = current->right;
        } else {
            current = current->left;
        }
    }
    return successor;
}


int main() {
    string s;
    int value;
    Node *node = nullptr;
    while (cin >> s >> value) {
        switch (s[0]) {
            case 'i':
                addNode(value, root);
                break;
            case 'e':
                cout << (returnNode(value, root) ? "true" : "false") << endl;
                break;
            case 'd':
                deleteNode(value, root);
                break;
            case 'n':
                node = nextNode(value);
                if (node) cout << node->key << endl;
                else cout << "none" << endl;
                break;
            case 'p':
                node = prevNode(value);
                if (node) cout << node->key << endl;
                else cout << "none" << endl;
                break;
        }
    }
    return 0;
}
