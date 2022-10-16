k = int(input())
A = [int(i) for i in input().split()]
C = [int(i) for i in input().split()]


def normalize(c):
    result = [0]
    for i in range(1, len(c) + 1):
        if c[-i] != 0:
            result = c[:len(c) - i + 1]
            break
    return result


p = [0, ] * k
for i in range(k):
    x = 0
    for j in range(i):
        x += A[i - j - 1] * C[j]
    p[i] = A[i] - x

p = normalize(p)
print(len(p) - 1)
print(*p)
print(k)
print(1, end=" ")
for i in C:
    print(-i, end=" ")
