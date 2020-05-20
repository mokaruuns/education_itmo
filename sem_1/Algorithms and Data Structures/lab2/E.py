numbers = [''] * 100
position = 0


def add(x):
    global position
    numbers[position] = x
    position += 1


def remove():
    global position
    position -= 1
    return numbers[position]


a = input().split()
sign = ["+", "-", "*"]
for i in a:
    if i not in sign:
        add(i)
    else:
        a = remove()
        b = remove()
        s = b + i + a
        add(str(eval(s)))
print(remove()) 
