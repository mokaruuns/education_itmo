import timeit

name_vars = dict()


class Disjunction:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def eval(self, i):
        return self.left.eval(i) or self.right.eval(i)


class Conjunction:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def eval(self, i):
        return self.left.eval(i) and self.right.eval(i)


class Implication:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def eval(self, i):
        return (not self.left.eval(i)) or self.right.eval(i)


class Variable:
    def __init__(self, value):
        self.value = value

    def eval(self, i):
        return (i >> name_vars[self.value]) & 1


class Negate:
    def __init__(self, value):
        self.value = value

    def eval(self, i):
        return not self.value.eval(i)


def get_lexemes(expression):
    var = ''
    for s in expression:
        if s.isalnum() or s == '\'':
            var += s
        elif var:
            yield var
            var = ''
        if s in "&|!()":
            yield s
        elif s in '-':
            yield '>'
    if var:
            yield var


class ExpressionParser:
    def __init__(self, expression):
        self.lexemes = list(get_lexemes(expression))
        self.pos = 0
        self.len_expr = len(self.lexemes)

    def expr(self):
        return self.parseImplication()

    def parseImplication(self):
        first = self.parseDisjunction()
        lexeme = self.next()
        while lexeme:
            if lexeme == '>':
                second = self.parseImplication()
                first = Implication(first, second)
            elif lexeme == ')':
                self.back()
                return first
            lexeme = self.next()
        return first

    def parseDisjunction(self):
        first = self.parseConjunction()
        cur = self.next()
        while cur:
            if cur == '|':
                second = self.parseConjunction()
                first = Disjunction(first, second)
            elif cur == ')' or cur == '>':
                self.back()
                return first
            cur = self.next()
        return first

    def parseConjunction(self):
        first = self.parseVariable()
        cur = self.next()
        while cur:
            if cur == '&':
                second = self.parseVariable()
                first = Conjunction(first, second)
            elif cur == ')' or cur == '>' or cur == '|':
                self.back()
                return first
            cur = self.next()
        return first

    def parseVariable(self):
        cur = self.next()
        if cur == '(':
            exp = self.parseImplication()
            self.next()
            return exp
        elif cur == '!':
            return Negate(self.parseVariable())
        else:
            if cur not in name_vars:
                name_vars[cur] = len(name_vars)
            return Variable(cur)

    def next(self):
        if self.len_expr <= self.pos:
            return None
        k = self.lexemes[self.pos]
        self.pos += 1
        return k

    def back(self):
        self.pos -= 1


# a = 'A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16 |  A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16 -> A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16 |  A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16'


a = input()


# A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16 |  A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16 -> A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16 |  A1 & A2 & A3 & A4 & A5 & A6 &A7 & A8 &A9 & A10 &A11 & A12 &A13 & A14 &A15 & A16
def solve():
    exp = ExpressionParser(a)
    x = exp.expr()
    vars = list(name_vars)
    positive = 0
    all_variants = 2 ** len(vars)
    for i in range(all_variants):
        positive += x.eval(i)
    if positive == 0:
        print('Unsatisfiable')
    elif positive == all_variants:
        print('Valid')
    else:
        print(f'Satisfiable and invalid, {positive} true and {all_variants - positive} false cases')


# print(timeit.timeit(solve, number=10) / 10)
solve()
