package expression.exceptions;

import expression.operations.IntegerOperation;
import expression.operations.Operation;

public class Main {
    public static void main(String[] args) {
//        TripleExpression cur = new ExpressionParser<>(IntegerOperation).parse(expression);
//        cur.evaluate(op.parseNumber(Integer.toString(x)),
//                                op.parseNumber(Integer.toString(y)),
//                                op.parseNumber(Integer.toString(z)));

        Operation<?> op = new IntegerOperation();
        ExpressionParser parser = new ExpressionParser<>(op);
        String s = "x+y";
        System.out.println(parser.parse(s).evaluate(1, 1, 1));
        System.out.println(parser.parse(s));
    }
}
