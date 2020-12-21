#include <bits/stdc++.h>

#define FOR(variable, begin, end) for (int variable = begin; variable < end; variable++)
typedef long long ll;

using namespace std;

const int N = 15;

void fastIO() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

struct Node {
    int left, right;
    int parent;
    int link;
    map<char, int> to;

    explicit Node(int left = 0, int right = 0, int parent = -1) : left(left), right(right),
                                                                  parent(parent), link(-1) {}

    int len() const { return right - left; }

    int &get(char c) {
        if (!to.count(c)) to[c] = -1;
        return to[c];
    }
};

Node g[N];
int size;
string str;

struct state {
    int vertex, position;

    state(int vertex, int position) : vertex(vertex), position(position) {}
};

state ptr(0, 0);

state go(state st, int left, int right) {
    while (right > left) {
        if (st.position == g[st.vertex].len()) {
            st = state(g[st.vertex].get(str[left]), 0);
            if (st.vertex == -1) return st;
        } else {
            if (str[g[st.vertex].left] + st.position != str[left]) {
                return {-1, -1};
            } else if (right - left < g[st.vertex].len() - st.position) {
                return {st.vertex, st.position + right - left};
            }
            left += g[st.vertex].len() - st.position;
            st.position = g[st.vertex].len();
        }
    }
    return st;
}

int split(state st) {
    if (st.position == g[st.vertex].len()) {
        return st.vertex;
    } else if (st.position == 0) {
        return g[st.vertex].parent;
    }
    Node vertex = g[st.vertex];
    int id = size++;
    g[id] = Node(vertex.left, vertex.left + st.position, vertex.parent);
    g[vertex.parent].get(str[vertex.left]) = id;
    g[id].get(str[vertex.left + st.position]) = st.vertex;
    g[st.vertex].parent = id;
    g[st.vertex].left += st.position;
    return id;
}

int get_link(int vertex) {
    if (g[vertex].link!= -1) return g[vertex].link;
    if (g[vertex].parent == -1) return 0;
    int to = get_link(g[vertex].parent);
    return g[vertex].link = split(go(state(to, g[to].len()),  g[vertex].left + (g[vertex].parent==0), g[vertex].right));
}

void tree_extend(int position) {
    while(true){
        state nptr = go (ptr, position, position+1);
        if (nptr.vertex != -1) {
            ptr = nptr;
            return;
        }
        int middle = split(ptr);
        int leaf = size++;
        g[leaf] = Node (position, str.size(), middle);
        g[middle].get(str[position]) = leaf;
        ptr.vertex = get_link(middle);
        ptr.position = g[ptr.vertex].len();
        if (!middle) break;
    }
}

void build_tree() {
    size = 1;
    FOR(i, 0, str.size()){
        tree_extend(str[i] - 'a');
    }
}


void solve() {
//    int n;
//    cin >> n;
//    vector<string> v_str;
//    FOR(i, 0, n) {
//        string basicString;
//        cin >> basicString;
//        v_str.emplace_back(basicString);
//    }
//    string final_str;
//    cin >> final_str;
    str = "abcaabbc";
    build_tree();
    cout << 1;
}

int main() {
    fastIO();
    solve();
    return 0;
}
