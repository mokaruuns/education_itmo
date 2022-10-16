import math
from multiprocessing import Process


def f(name):
    print('hello', name)


def fact(n):
    print(math.factorial(n))


if __name__ == '__main__':
    proc = []
    proc.append(Process(target=fact, args=(500,)))
    proc.append(Process(target=fact, args=(100,)))
    proc.append(Process(target=fact, args=(5,)))
    for p in proc:
        p.start()
    for p in proc:
        p.join()
