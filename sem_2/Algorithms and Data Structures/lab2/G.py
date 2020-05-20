
from random import randint

MAX_INT = 2 ** 32 - 1


class Node:
    def __init__(self, left, right, value, size, y):
        self.left = left
        self.right = right
        self.value = value
        self.size = size
        self.y = y


class Pair:
    def __init__(self, first, second):
        self.first = first
        self.second = second


def create_node(value):
    return Node(None, None, value, 1, randint(0, MAX_INT))


def size_of(A):
    return 0 if A is None else A.size


def update(A):
    A.size = size_of(A.left) + size_of(A.right) + 1


def merge(A, B):
    if not A:
        return B
    if not B:
        return A
    if A.y > B.y:
        A.right = merge(A.right, B)
        update(A)
        return A
    else:
        B.left = merge(A, B.left)
        update(B)
        return B


def split(A, pos):
    if not A:
        return Pair(None, None)
    if size_of(A.left) >= pos:
        tmp = split(A.left, pos)
        A.left = tmp.second
        update(A)
        return Pair(tmp.first, A)
    else:
        tmp = split(A.right, pos - size_of(A.left) - 1)
        A.right = tmp.first
        update(A)
        return Pair(A, tmp.second)


def insert(A, pos, x):
    tmp = split(A, pos)
    node = create_node(x)
    merge_left = merge(tmp.first, node)
    merge_right = merge(merge_left, tmp.second)
    return merge_right


def remote(A, pos):
    A = split(A, pos)
    B = split(A.second, pos + 1)
    return merge(A.first, B.second)


def reverse(A, left, right):
    tmp1 = split(A, left - 1)
    tmp2 = split(tmp1.second, right - left + 1)
    return merge(merge(tmp2.first, tmp1.first), tmp2.second)


def printInOrder(A):
    if A:
        if A.left:
            printInOrder(A.left)
        print(A.value, end=' ')
        if A.right:
            printInOrder(A.right)


root = None

n, m = [int(i) for i in input().split()]
for i in range(n):
    root = insert(root, i, i + 1)
for i in range(m):
    left, right = [int(i) for i in input().split()]
    root = reverse(root, left, right)
printInOrder(root)
