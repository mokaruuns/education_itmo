numbers = [0] * 100000
position = 0
result = [0] * 100000
position_result = 0
result_string = [''] * 100000
position_result_string = 0

def add(x):
    global position
    numbers[position] = x
    position += 1


def add_result(x):
    global position_result
    result[position_result] = x
    position_result += 1


def add_string(x):
    global position_result_string
    result_string[position_result_string] = x
    position_result_string += 1

def remove():
    global position
    position -= 1
    return numbers[position]


n = int(input())
a = list(map(int, input().split()))

for i in a:
    while (position > 0) and (i >= numbers[position - 1]):
        add_result(remove())
        add_string("pop")
    add(i)
    add_string("push")
while position > 0:
    add_result(remove())
    add_string("pop")
for i in range(n - 1):
    if result[i] > result[i + 1]:
        print("impossible")
        exit()
for i in range(position_result_string):
    print(result_string[i]) 
