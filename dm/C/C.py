MOD = 10 ** 12


def normalize(c):
    result = [0]
    for i in range(1, len(c) + 1):
        if c[-i] != 0:
            result = c[:len(c) - i + 1]
            break
    return result


def get_element(lst, index):
    if index >= len(lst):
        return 0
    else:
        return lst[index]


def mult_rows(a, b):
    c = [0, ] * (len(a) + len(b) + 1)
    for i in range(len(a)):
        for j in range(len(b)):
            c[i + j] = (c[i + j] + (a[i] * b[j]) % MOD) % MOD
    return normalize(c)


def getQ(C):
    result = [1]
    for i in C:
        result.append(-i)
    return result


def getP(A, Q):
    return mult_rows(A, Q)


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        k = map(int, file.readline().split())
        A = [int(i) for i in file.readline().split()]
        C = [int(i) for i in file.readline().split()]
    return k, A, C


def read_right():
    k = int(input())
    A = [int(i) for i in input().split()]
    C = [int(i) for i in input().split()]
    return k, A, C


# k, A, C = read_file("c.in", "c.out")
k, A, C = read_right()
Q = getQ(C)
P = getP(A, Q)[:k]
P = normalize(P)
print(len(P) - 1)
print(*P)
print(k)
print(*Q)
