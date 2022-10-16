MOD = 998244353


def normalize(c):
    result = [0]
    for i in range(1, len(c) + 1):
        if c[-i] != 0:
            result = c[:len(c) - i + 1]
            break
    return result


def bin_pow(x, index):
    result = 1
    while index:
        if index % 2:
            result = (result * x) % MOD
        x = (x * x) % MOD
        index //= 2
    return result


def reverse_element(a, b):
    return a * bin_pow(b, MOD - 2) % MOD


def find(index):
    a = 1
    b = 1
    for i in range(index):
        a = (a * ((1 - 2 * i) % MOD)) % MOD
        b = (b * (((i + 1) * 2) % MOD)) % MOD
    return reverse_element(a, b)


def mult_rows(a, b):
    size = min(len(a) + len(b), m)
    c = [0, ] * size
    for i in range(size):
        for j in range(i + 1):
            c[i] = (c[i] + (get_element(a, j) * get_element(b, i - j)) % MOD) % MOD
    return normalize(c)


def get_element(lst, index):
    if index >= len(lst):
        return 0
    else:
        return lst[index]


def sqrt():
    result = [0, ] * m
    result[0] = 1
    a = [1]
    for i in range(1, m):
        f = find(i)
        if f < 0:
            f += MOD
        a = mult_rows(a, P)
        for j in range(m):
            result[j] = (result[j] + f * get_element(a, j) % MOD) % MOD
    return result


def exp():
    result = [0, ] * m
    result[0] = 1
    a = [1]
    f = 1
    for i in range(1, m):
        f = (f * i) % MOD
        a = mult_rows(a, P)
        for j in range(m):
            result[j] = (result[j] + get_element(a, j) * bin_pow(f, MOD - 2) % MOD) % MOD
    return result


def log():
    result = [0, ] * m
    result[0] = 1
    a = [1]
    f = MOD - 1
    for i in range(1, m):
        f = MOD - f
        a = mult_rows(a, P)
        for j in range(m):
            result[j] = (result[j] + get_element(a, j) * (f * bin_pow(i, MOD - 2) % MOD) % MOD) % MOD
    result[0] = 0
    return result


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        n, m = map(int, file.readline().split())
        P = [int(i) for i in file.readline().split()]
    return n, m, P


def read_right():
    n, m = map(int, input().split())
    P = [int(i) for i in input().split()]
    return n, m, P


# n, m, P = read_file("b.in", "b.out")
n, m, P = read_right()
print(*sqrt())
print(*exp())
print(*log())
