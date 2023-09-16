def get_indexes(arr):
    sorted_list = sorted(arr)
    index_dict = {value: index for index, value in enumerate(sorted_list)}
    indexes_in_sorted = [index_dict[j] for j in arr]
    return indexes_in_sorted


n = int(input())
d = 0
x, y = [], []
for i in range(n):
    x_i, y_i = map(int, input().strip().split())
    x.append(x_i)
    y.append(y_i)

x = get_indexes(x)
y = get_indexes(y)
d = 0
for i in range(n):
    d += (x[i] - y[i]) ** 2

r = 1 - 6 * d / (n ** 3 - n)

print(r)
