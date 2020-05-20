from math import log2
n = 0
s = []
dp = []

def binary_ups():
    n = int(input())
    s = [int(i) for i in input().split()]
    dp = [[0 for i in range(20)] for j in range(500001)]
    for i in range(0, n):
        dp[i + 1][0] = s[i]
    for j in range(1, 20):
        for i in range(1, n + 1):
            dp[i][j] = dp[dp[i][j - 1]][j - 1]
    for i in range(1, n + 1):
        print(i, ": ", sep='', end='')
        for j in dp[i]:
            if j <= 0:
                break
            else:
                print(j, end=' ')
        print()

binary_ups()
