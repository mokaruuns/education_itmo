from collections import defaultdict

k1, k2 = map(int, input().split())
n = int(input())
table = defaultdict(lambda: 0.0)
sum_up = [0] * (k1 + 1)
sum_right = [0] * (k2 + 1)
for i in range(n):
    x, y = map(int, input().split())
    table[(x, y)] += 1
    sum_up[x] += 1
    sum_right[y] += 1
xi = 0

for (x, y), value in table.items():
    expected = sum_right[y] * sum_up[x] / n
    xi += (expected ** 2 - (value - expected) ** 2) / expected

print(n - xi)
