def read_file(f_in, f_out):
    with open(f_in, 'r') as file:
        return map(int, file.readline().split())


def read_right():
    return map(int, input().split())


def norm(x):
    return MOD if x < 0 else 0


def fill_triangle():
    triangle = [[0, ] * (k + 1) for _ in range(k + 1)]
    for i in range(k + 1):
        triangle[i][0] = 1
        triangle[i][i] = 1
        for j in range(1, i):
            triangle[i][j] = (triangle[i - 1][j] + triangle[i - 1][j - 1]) % MOD
    return triangle


def get_tree(n, k):
    return f[n] // (f[k] * f[n - k])


def fill_lr(v):
    arr = [0, ] * 5001
    for i in range(k - v):
        if 2 * i < k - v:
            arr[i] = get_tree(k - i - 1 - v, i) * (1 if i % 2 == 0 else -1)
            # arr[i] = tree[k - i - 1 - v][i] * (1 if i % 2 == 0 else -1)
    return arr


k, n = read_right()
MOD = 998244353
f = [1]
for i in range(1, k + 1):
    f.append(i * f[-1] % MOD)
# tree = fill_triangle()
left = fill_lr(1)
right = fill_lr(0)
t = [0, ] * 5001
t[0] = 1 // right[0]
for i in range(n + 1):
    for j in range(i-1, -1, -1):
        if i - j > k:
            break
        t[i] = (t[i] - (t[j] * right[i - j]) % MOD) % MOD
    t[i] //= right[0]
for i in range(n):
    result = 0
    for j in range(i + 1):
        if j >= k:
            break
        result = (result + (left[j] * t[i - j]) % MOD) % MOD
    print(result)
