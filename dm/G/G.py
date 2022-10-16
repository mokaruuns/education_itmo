from math import gcd
from math import factorial
import functools, operator
from itertools import zip_longest


def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        d = file.readline()
    return d


def read_right():
    d = input()
    return d


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


obj = read_file('g.in', 'g.out')

c = obj[0]
#
# if c == 'B':
#     result = [0, 1, 0, 0, 0, 0, 0]
# else:
