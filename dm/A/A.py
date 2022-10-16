from itertools import zip_longest

MOD = 998244353


def normalize(c):
    result = [0]
    for i in range(1, len(c) + 1):
        if c[-i] != 0:
            result = c[:len(c) - i + 1]
            break
    return result


def sum_rows(a, b):
    c = list(map(lambda x: (x[0] + x[1]) % MOD, zip_longest(a, b, fillvalue=0)))
    return normalize(c)


def mult_rows(a, b):
    c = [0, ] * (len(a) + len(b))
    for i in range(len(a)):
        for j in range(len(b)):
            c[i + j] = (c[i + j] + (a[i] * b[j]) % MOD) % MOD
    return normalize(c)


def get_element(lst, index):
    if index >= len(lst):
        return 0
    else:
        return lst[index]


def div_rows(P, Q):
    dp = [0, ] * 1000
    dp[0] = 1
    for i in range(1000):
        for j in range(1, i + 1):
            dp[i] = (dp[i] - (get_element(Q, j) * get_element(dp, i - j)) % MOD) % MOD
    ans = [0, ] * 1000
    for i in range(len(ans)):
        for j in range(0, i + 1):
            ans[i] = (ans[i] + (get_element(P, j) * get_element(dp, i - j)) % MOD) % MOD
    return ans


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        n, m = map(int, file.readline().split())
        P = [int(i) for i in file.readline().split()]
        Q = [int(i) for i in file.readline().split()]
    return n, m, P, Q


def read_right():
    n, m = map(int, input().split())
    P = [int(i) for i in input().split()]
    Q = [int(i) for i in input().split()]
    return n, m, P, Q


# n, m, P, Q = read_file("A/a.in", "A/a.out")
n, m, P, Q = read_right()

sum_r = sum_rows(P, Q)
print(len(sum_r) - 1)
print(*sum_r)

mult_r = mult_rows(P, Q)
print(len(mult_r) - 1)
print(*mult_r)

div_r = div_rows(P, Q)
print(*div_r)
