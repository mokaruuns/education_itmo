from itertools import zip_longest
from math import gcd
from math import factorial


def normalize(c):
    result = [0]
    for i in range(1, len(c) + 1):
        if c[-i] != 0:
            result = c[:len(c) - i + 1]
            break
    return result


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        r, k = map(int, file.readline().split())
        P = [int(i) for i in file.readline().split()]
    return r, k, P


def read_right():
    r, k = map(int, input().split())
    P = [int(i) for i in input().split()]
    return r, k, P


def bin_pow(x, index):
    result = 1
    while index:
        if index % 2:
            result = (result * x)
        x = (x * x)
        index //= 2
    return result


def get_element(lst, index):
    if index >= len(lst):
        return 0
    else:
        return lst[index]


def sum_rows(a, b):
    c = list(map(lambda x: (x[0] + x[1]), zip_longest(a, b, fillvalue=0)))
    return normalize(c)


def mult_rows(a, b):
    c = [0, ] * (len(a) + len(b) + 1)
    for i in range(len(a)):
        for j in range(len(b)):
            c[i + j] += (a[i] * b[j])
    return normalize(c)


# r, k, P = read_file("d.in", "d.out")
r, k, P = read_right()
x = bin_pow(r, k)
fact = factorial(k)
ans = [0]
for i in range(k + 1):
    tmp = [P[i] * bin_pow(r, k - i)]
    for j in range(1, k + 1):
        tmp = mult_rows(tmp, [j - i, 1])
    ans = sum_rows(ans, tmp)

for i in range(k + 1):
    g = gcd(abs(get_element(ans, i)), fact * x)
    print(get_element(ans, i) // g, fact * x // g, sep='/', end=' ')
