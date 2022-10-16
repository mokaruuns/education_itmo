from math import gcd
from math import factorial
import functools, operator
from itertools import zip_longest

euler = [
    [1],
    [1],
    [1, 1],
    [1, 4, 1],
    [1, 11, 11, 1],
    [1, 26, 66, 26, 1],
    [1, 57, 302, 302, 57, 1],
    [1, 120, 1191, 2416, 1191, 120, 1],
    [1, 247, 4293, 15619, 15619, 4293, 247, 1],
    [1, 502, 14608, 88234, 156190, 88234, 14608, 502, 1],
    [1, 1013, 47840, 455192, 1310354, 1310354, 455192, 47840, 1013, 1],
]


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        r = int(file.readline())
        d = int(file.readline())
        P = [int(i) for i in file.readline().split()]
    return r, d, P


def read_right():
    r = int(input())
    d = int(input())
    P = [int(i) for i in input().split()]
    return r, d, P


def sum_rows(a, b, k=1):
    c = list(map(lambda x: (x[0] + x[1] * k), zip_longest(a, b, fillvalue=0)))
    return normalize(c)


def normalize(c):
    result = [0]
    for i in range(1, len(c) + 1):
        if c[-i] != 0:
            result = c[:len(c) - i + 1]
            break
    return result


def mult_rows(a, b):
    c = [0, ] * (len(a) + len(b) + 1)
    for i in range(len(a)):
        for j in range(len(b)):
            c[i + j] += (a[i] * b[j])
    return normalize(c)


r, d, P = read_right()
pw = [1]
for i in range(1, 12):
    pw.append(pw[-1] * r)
t = [1, -r]
f = [0]
z = [1]

for i in range(d, -1, -1):
    if i == 0:
        w = [1]
    else:
        w = [0]
        for j in range(0, i):
            w.append(euler[i][j] * pw[j])
    f = sum_rows(f, mult_rows(w, z), P[i] * (1 if i == 0 else r))
    z = mult_rows(z, t)

print(len(f) - 1)
print(*f)
print(len(z) - 1)
print(*z)
