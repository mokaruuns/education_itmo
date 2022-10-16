MOD = 10 ** 9 + 7


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        k, m = map(int, file.readline().split())
        C = [int(i) for i in file.readline().split()]
    return k, m, C


def read_right():
    k, m = map(int, input().split())
    C = [int(i) for i in input().split()]
    return k, m, C


k, m, C = read_right()
n = m + 1
result = [0, ] * n
result[0] = 1
t = [0, ] * n
t[0] = 1
for i in range(1, n):
    for j in range(k):
        if i >= C[j]:
            result[i] = (result[i] + t[i - C[j]]) % MOD
    for j in range(i + 1):
        t[i] = (t[i] + (result[j] * result[i - j]) % MOD) % MOD
print(*result[1:])
