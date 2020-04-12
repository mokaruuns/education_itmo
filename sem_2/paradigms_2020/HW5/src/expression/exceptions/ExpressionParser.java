package expression.exceptions;

import expression.*;
import expression.generic.TripleExpression;
import expression.operations.Operation;

import java.util.List;


public class ExpressionParser<T> {
    private Operation<T> op;

    public ExpressionParser(Operation<T> op) {
        this.op = op;
    }

    public TripleExpression<T> parse(String expression) {
        return parse(new StringSource(expression));
    }

    private TripleExpression<T> parse(StringSource stringSource) {
        return new Parser(stringSource).parseExpression();
    }

    private class Parser extends BaseParser {
        int balance = 0;

        public Parser(final StringSource stringSource) {
            super(stringSource);
            nextChar();
            skipWhitespace();
        }

        public TripleExpression<T> parseExpression() {
            TripleExpression<T> result = parseSumAndSub();
            if (balance > 0) throw errorWithoutPos("No close bracket");
            return result;
        }

        private TripleExpression<T> parseSumAndSub() {
            TripleExpression<T> res = parseMulAndDiv();
            while (!test('\0')) {
                if (test(')')) {
                    balance--;
                    break;
                }
                skipWhitespace();
                if (test('+')) res = new Add(res, parseMulAndDiv(), op);
                else if (test('-')) res = new Subtract(res, parseMulAndDiv(), op);
                else throw errorWithPos(getMessage("+ or -"));
            }
            if (balance < 0) throw errorWithoutPos("No open bracket");
            return res;
        }

        private TripleExpression<T> parseMulAndDiv() {
            TripleExpression<T> res = parseValAndVar();
            while (!test('\0')) {
                skipWhitespace();
                if (test('*')) res = new Multiply(res, parseValAndVar(), op);
                else if (test('/')) res = new Divide(res, parseValAndVar(), op);
                else break;
            }
            return res;
        }

        private TripleExpression<T> parseValAndVar() {
            skipWhitespace();
            if (test('-'))
                return between('0', '9') ? new Const<T>(parseNumber(true)) : new Negate(parseValAndVar(), op);
            else if (between('0', '9')) return new Const<T>(parseNumber(false));
            else if (test('(')) {
                balance++;
                return parseSumAndSub();
            } else for (char variable : List.of('x', 'y', 'z'))
                if (test(variable)) return new Variable(Character.toString(variable));
            throw errorWithPos(getMessage("x, y, z, constant or variable"));
        }

        private T parseNumber(boolean isNegative) {
            final StringBuilder sb = new StringBuilder();
            if (isNegative) sb.append('-');
            copyDigits(sb);
            try {
                T res = op.parseNumber(sb.toString());
                return res;
            } catch (NumberFormatException e) {
                throw errorWithoutPos("Invalid number " + sb);
            }
        }

        private void copyDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
                // skip
            }
        }
    }
}