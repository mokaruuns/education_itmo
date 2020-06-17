#include <bits/stdc++.h>

#define fori(start, finish) for (int i = start; i < finish; i++)
#define forj(start, finish) for (int j = start; j < finish; j++)
#define forn(finish) for (int i = 0; i < finish; i++)
typedef long long ll;
typedef unsigned long long ull;

using namespace std;

void open_file_rw() {
    char *in = "linkedmap.in";
    char *out = "linkedmap.out";
    freopen(in, "r", stdin);
    freopen(out, "w", stdout);
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
}

ll A = rand() % 10000 + 1;
ll prime = 10000019;
ll M = 1e6;


struct Node {
    string key = "_";
    string value = "_";
    Node *prev = nullptr;
    Node *next = nullptr;
};

Node *current = new Node();

list<struct Node *> my_set[1000000];

// ll get_hash(ll number) { return ((A * (number + INT32_MAX)) % prime) % M; }
ll get_hash_string(string str) {
    ull h = 0;
    for (char c : str) {
        h = 33 * h ^ c;
    }
    return h % M;
}

Node *createNode(string key, string value) {
    Node *node = new Node;
    node->key = key;
    node->value = value;
    node->prev = current;
    node->next = nullptr;
    return node;
}

void my_insert(string key, string value, ll h) {
    for (auto iter = my_set[h].begin(); iter != my_set[h].end(); iter++) {
        if ((*iter)->key == key) {
            (*iter)->value = value;
            return;
        }
    }
    Node *new_Node = createNode(key, value);
    current->next = new_Node;
    current = new_Node;
    my_set[h].push_back(new_Node);
}

void my_delete(string key, ll h) {
    for (auto iter = my_set[h].begin(); iter != my_set[h].end(); iter++) {
        if (*iter && (*iter)->key == key) {
            if (current && current->key == key) {
                current = current->prev;
            }
            if ((*iter)->prev) (*iter)->prev->next = (*iter)->next;
            if ((*iter)->next) (*iter)->next->prev = (*iter)->prev;
            my_set[h].erase(iter);
            return;
        }
    }
}

string my_exist(string key, ll h) {
    for (auto p : my_set[h]) {
        if (p->key == key) {
            return p->value;
        }
    }
    return "none";
}

string prev(string key, ll h) {
    string prev_value = "none";
    for (auto p : my_set[h]) {
        if (p->key == key && p->prev && p->prev->value != "_") {
            prev_value = p->prev->value;
            return prev_value;
        }
    }
    return prev_value;
}

string next(string key, ll h) {
    string next_value = "none";
    for (auto p : my_set[h]) {
        if (p->key == key && p->next && p->next->value!= "_") {
            next_value = p->next->value;
            return next_value;
        }
    }
    return next_value;
}


int main() {
    open_file_rw();
    string request;
    string key, value;
    while (cin >> request >> key) {
        ll h = get_hash_string(key);
        if (request == "put") {
            cin >> value;
            my_insert(key, value, h);
        } else if (request == "delete") {
            my_delete(key, h);
        } else if (request == "get") {
            cout << my_exist(key, h) << "\n";
        } else if (request == "prev") {
            cout << prev(key, h) << "\n";
        } else if (request == "next") {
            cout << next(key, h) << "\n";
        }
    }
}