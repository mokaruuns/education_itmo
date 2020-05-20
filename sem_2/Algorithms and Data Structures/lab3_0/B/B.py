from math import log2, ceil

n = 0
dp = []
d = []


def binary_ups():
    global d, n, dp
    n = int(input())
    d = [0 for i in range(200010)]
    dp = [[0 for i in range(19)] for j in range(200010)]
    for i in range(n - 1):
        x = int(input())
        dp[i + 2][0] = x
        d[i + 2] = d[x] + 1
    k = ceil(log2(n))
    for j in range(1, k):
        for i in range(1, n + 1):
            dp[i][j] = dp[dp[i][j - 1]][j - 1]


def get_lca(v, u):
    if d[v] < d[u]:
        v, u = u, v
    while d[v] != d[u]:
        diff = d[v] - d[u]
        v = dp[v][int(log2(diff))]
    if v == u:
        return v
    else:
        if d[v] != 0:
            k = ceil(log2(d[v]))
        for i in range(k, -1, -1):
            if dp[v][i] != dp[u][i]:
                v = dp[v][i]
                u = dp[u][i]
        return dp[v][0]


def requests():
    global d, n, dp
    m = int(input())
    for i in range(m):
        v, u = map(int, input().split())
        print(get_lca(v, u))


binary_ups()
requests()
