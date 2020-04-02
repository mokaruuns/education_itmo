package expression;

import parser.Parser;

import static expression.Operator.*;

public class ExpressionParser implements Parser {
    int position;
    int len_str;
    String expression;
    CommonExpression res;

    public CommonExpression parse(String expression) {
        position = 0;
        len_str = expression.length();
        this.expression = expression;
        if (getNextOperator(position) == Operator.WHITESPACE) {
            movePosition();
        }
        return parseShifts();

    }

    private CommonExpression parseShifts() {
        CommonExpression res = parseSumAndSub();
        Operator current = getNextOperator(position);
        flag:
        while (current != END_LINE) {
            switch (current) {
                case RIGHT_SHIFT:
                    movePosition();
                    res = new RightShift(res, parseSumAndSub());
                    break;
                case LEFT_SHIFT:
                    movePosition();
                    res = new LeftShift(res, parseSumAndSub());
                    break;
                case BRACKET_RIGHT:
                    break flag;
            }
            current = getNextOperator(position);
        }
        return res;
    }

    private CommonExpression parseSumAndSub() {
        res = parseMulAndDiv();
        Operator current = getNextOperator(position);
        flag:
        while (current != END_LINE) {
            switch (current) {
                case ADD:
                case SUBTRACT:
                    movePosition();
                    res = getAction(current);
                    break;
                case BRACKET_RIGHT:
                case RIGHT_SHIFT:
                case LEFT_SHIFT:
                    break flag;
            }
            current = getNextOperator(position);
        }
        return res;
    }

    private CommonExpression parseMulAndDiv() {
        res = parseValAndVar();
        Operator cur = getNextOperator(position);
        flag:
        while (cur != END_LINE) {
            switch (cur) {
                case MULTIPLY:
                case DIVIDE:
                    movePosition();
                    res = getAction(cur);
                    break;
                case BRACKET_RIGHT:
                case RIGHT_SHIFT:
                case LEFT_SHIFT:
                case ADD:
                case SUBTRACT:
                    break flag;
            }
            cur = getNextOperator(position);
        }
        return res;
    }

    private CommonExpression parseValAndVar() {
        Operator current = getNextOperator(position);
        CommonExpression res = null;
        switch (current) {
            case DIGIT:
            case BRACKET_LEFT:
            case VARIABLE:
                res = getAction(current);
                break;
            case SUBTRACT:
                if (getNextOperator(position + getNextOperator(position).getLength()) == DIGIT) {
                    res = new Const(getInt());
                } else {
                    movePosition();
                    res = new NegativeConst(parseValAndVar());
                }
                break;
        }
        return res;
    }


    private Operator getNextOperator(int pos) {
        if (pos >= len_str)
            return END_LINE;
        for (Operator A : Operator.values()) {
            if (len_str - pos >= A.getLength() && expression.substring(pos, pos + A.getLength()).equals(A.getStringOperator())) {
                return A;
            }
        }
        if (Character.isDigit(expression.charAt(pos))) {
            return DIGIT;
        }
        if (Character.isWhitespace(expression.charAt(pos))) {
            return WHITESPACE;
        }
        if (expression.charAt(pos) == 'x' || expression.charAt(pos) == 'y' || expression.charAt(pos) == 'z') {
            return VARIABLE;
        }
        return null;
    }

    private void movePosition() {
        position += getNextOperator(position).getLength();
        while (getNextOperator(position) == WHITESPACE) {
            position++;
        }
    }

    private int getInt() {
        int begin = position;
        int end = position;
        if (getNextOperator(position) == SUBTRACT) {
            end++;
            movePosition();
        }
        while (getNextOperator(position) == DIGIT) {
            end++;
            movePosition();
        }
        String number = expression.substring(begin, end);
        return Integer.parseInt(number);
    }

    private CommonExpression getAction(Operator operator) {
        CommonExpression result = null;
        switch (operator) {
            case ADD:
                return new Add(res, parseMulAndDiv());
            case SUBTRACT:
                return new Subtract(res, parseMulAndDiv());
            case MULTIPLY:
                return new Multiply(res, parseValAndVar());
            case DIVIDE:
                return new Divide(res, parseValAndVar());
            case VARIABLE:
                result = new Variable(String.valueOf(expression.charAt(position)));
                movePosition();
                break;
            case DIGIT:
                return new Const(getInt());
            case BRACKET_LEFT:
                movePosition();
                result = parseShifts();
                movePosition();
                break;
        }
        return result;
    }
}