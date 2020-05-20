from sys import stdin
n = int(input())
p = []
mini = []
maxi = []
size = [1]*(n+1)


def find(x):
    if p[x] != x:
        p[x] = find(p[x])
    return p[x]


def union(x, y):  # x,y - элементы
    x_p = find(x)
    y_p = find(y)
    if x_p!=y_p:
        if size[x_p] > size[y_p]:
            x_p, y_p = y_p, x_p
        p[x_p] = y_p
        mini[x_p] = min(mini[x_p], mini[y_p])
        mini[y_p] = mini[x_p]
        maxi[x_p] = max(maxi[x_p], maxi[y_p])
        maxi[y_p] = maxi[x_p]
        size[x_p] += size[y_p]
        size[y_p] = size[x_p]





for i in range(n+1):
    p.append(i) # [[элементы мн-ва],[min, max, size]]
    mini.append(i)
    maxi.append(i)
for line in stdin:
    a = str(line).split()
    t = a[0]
    if t == 'union':
        union(int(a[1]), int(a[2]))
    else:
        z = find(int(a[1]))
        print(mini[z], maxi[z], size[z])
 
