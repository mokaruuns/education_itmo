package expression;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(CommonExpression first) {
        super(first);
    }

    @Override
    public String getUnary() {
        return "-";
    }

    @Override
    public int makeSomethingInt(int x) {
        if (x != Integer.MIN_VALUE) {
            return -x;
        }
        throw new BinaryOperationException("result of negate is too big for int");
    }
}


