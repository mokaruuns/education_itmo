package parser;

import expression.*;

import java.util.List;


public class ExpressionParser {
    public CommonExpression parse(String expression) {
        return parse(new StringSource(expression));
    }

    private CommonExpression parse(StringSource stringSource) {
        return new Parser(stringSource).parseExpression();
    }

    private static class Parser extends BaseParser {
        int balance = 0;

        public Parser(final StringSource stringSource) {
            super(stringSource);
            nextChar();
            skipWhitespace();
        }

        public CommonExpression parseExpression() {
            CommonExpression result = parseSumAndSub();
            if (balance > 0) throw errorWithoutPos("No close bracket");
            return result;
        }

        private CommonExpression parseSumAndSub() {
            CommonExpression res = parseMulAndDiv();
            while (!test('\0')) {
                if (test(')')) {
                    balance--;
                    break;
                }
                skipWhitespace();
                if (test('+')) res = new CheckedAdd(res, parseMulAndDiv());
                else if (test('-')) res = new CheckedSubtract(res, parseMulAndDiv());
                else throw errorWithPos(getMessage("+ or -"));
            }
            if (balance < 0) throw errorWithoutPos("No open bracket");
            return res;
        }

        private CommonExpression parseMulAndDiv() {
            CommonExpression res = parseValAndVar();
            while (!test('\0')) {
                skipWhitespace();
                if (test('*')) res = new CheckedMultiply(res, parseValAndVar());
                else if (test('/')) res = new CheckedDivide(res, parseValAndVar());
                else break;
            }
            return res;
        }

        private CommonExpression parseValAndVar() {
            skipWhitespace();
            if (test('-'))
                return between('0', '9') ? new Const(parseNumber(true)) : new CheckedNegate(parseValAndVar());
            else if (between('0', '9')) return new Const(parseNumber(false));
            else if (test('l')) {
                expect("og2");
                for (char variable : List.of('x', 'y', 'z'))
                    if (test(variable)) throw errorWithPos("Variable is not separated");
                return new CheckedLog(parseValAndVar());
            } else if (test('p')) {
                expect("ow2");
                return new CheckedPow(parseValAndVar());
            } else if (test('(')) {
                balance++;
                return parseSumAndSub();
            } else for (char variable : List.of('x', 'y', 'z'))
                if (test(variable)) return new Variable(Character.toString(variable));
            throw errorWithPos(getMessage("x, y, z, constant or variable"));
        }

        private int parseNumber(boolean isNegative) {
            final StringBuilder sb = new StringBuilder();
            if (isNegative) sb.append('-');
            copyDigits(sb);
            try {
                return Integer.parseInt(sb.toString());
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