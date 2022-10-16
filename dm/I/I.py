from itertools import zip_longest


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        k, n = map(int, file.readline().split())
        A = [int(i) for i in file.readline().split()]
        C = [int(i) for i in file.readline().split()]
        return k, n, A, C


def read_right():
    k, n = map(int, input().split())
    A = [int(i) for i in input().split()]
    C = [int(i) for i in input().split()]
    return k, n, A, C


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
            c[i + j] = (c[i + j] + ((a[i] * b[j]) % MOD)) % MOD
    return c


def norm(x):
    return MOD if x < 0 else 0


# k, n, A, C = read_file("i.in", "i.out")
k, n, A, C = read_right()
MOD = 104857601
new_C = [1]
for i in C:
    new_C.append((-i + MOD) % MOD)
n -= 1
n1 = n
A.extend([0, ] * k)
while n1 >= k:
    for i in range(k, 2 * k):
        A[i] = 0
        for j in range(1, k + 1):
            A[i] = (A[i] - ((new_C[j] * A[i - j]) % MOD)) % MOD
            A[i] = A[i] + norm(A[i])
    S = [(new_C[i] if i % 2 == 0 else (-new_C[i] + MOD) % MOD) for i in range(k + 1)]
    M = mult_rows(new_C, S)
    new_C = [M[i * 2] for i in range(k + 1)]
    for i in range(n1 % 2, 2 * k, 2):
        A[i // 2] = A[i]
    n1 //= 2
print(A[n1])
