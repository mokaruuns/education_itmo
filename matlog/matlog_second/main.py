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


class Lexeme:

    def __init__(self, expr):
        self.expr = expr
        self.pos = 0

    def back(self):
        if 0 <= self.pos - 1 < len(self.expr):
            return self.expr[self.pos - 1]
        else:
            return None

    def get_next(self):
        if 0 <= self.pos + 1 < len(self.expr):
            return self.expr[self.pos + 1]
        else:
            return None

    def next(self):
        self.pos += 1

    def current(self):
        if 0 <= self.pos < len(self.expr):
            return self.expr[self.pos]
        else:
            return None

    def get_lexemes(self):
        lexemes = []
        var = ''
        while self.current():
            s = self.current()
            if s.isalnum() or s == '\'':
                var += s
                self.next()
            elif var:
                lexemes.append(var)
                var = ''
            elif s in '&!()':
                lexemes.append(s)
                self.next()
            elif s == '|':
                if self.get_next() == '-':
                    lexemes.append('|-')
                    self.next()
                else:
                    lexemes.append('|')
                self.next()
            elif s == '-':
                if self.get_next() == '>':
                    lexemes.append('->')
                    self.next()
                self.next()
            else:
                self.next()
        if var:
            lexemes.append(var)
        return lexemes


class ExpressionParser:

    def __init__(self, expression):
        self.lexemes = Lexeme(expression).get_lexemes()
        self.pos = 0
        self.len_expr = len(self.lexemes)

    def expr(self):
        return self.parseImplication()

    def parseImplication(self):
        first = self.parseDisjunction()
        lexeme = self.next()
        while lexeme:
            if lexeme == '->':
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


def read_file(name):
    with open(name, 'r') as f:
        for line in f:
            exp = ExpressionParser(line)
            x = exp.expr()
            print(exp.lexemes)


read_file("input")
