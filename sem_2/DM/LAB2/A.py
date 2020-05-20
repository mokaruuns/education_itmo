fin = open('problem1.in', 'r')
fout = open('problem1.out', 'w')
s = fin.readline().strip()
n, m, k = map(int, fin.readline().strip().split())
act = [int(i) for i in fin.readline().strip().split()]
p = {}
for i in range(m):
    a, b, c = fin.readline().strip().split()
    a = int(a)
    b = int(b)
    if a in p:
        p[a].update({c: b})
    else:
        p[a] = {c: b}
cur = 1
for i in s:
    if (cur in p) and (i in p[cur]):
        cur = p[cur][i]
    else:
        fout.write("Rejects")
        exit()
if cur in act:
    fout.write("Accepts")
else:
    fout.write("Rejects")