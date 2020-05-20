from random import randint


def qsort(a, l, r):
    if r > l:
        x = a[randint(l, r - 1)]
        m1, m2 = qsplit(a, l, r, x)
        qsort(a, l, m1)
        qsort(a, m2, r)


def qsplit(a, l, r, x):
    m1 = l
    m2 = l
    for i in range(l, r):
        if a[i] < x:
            tmp = a[i]
            a[i] = a[m2]
            a[m2] = a[m1]
            a[m1] = tmp
            m1 += 1
            m2 += 1
            continue
        if a[i] == x:
            a[i], a[m2] = a[m2], a[i]
            m2 += 1
    return m1, m2


n = int(input())
a = [int(i) for i in input().split()]
qsort(a, 0, n)
print(*a)
 
