package expression;

public class CheckedPow extends UnaryOperation {

    public CheckedPow(CommonExpression first) {
        super(first);
    }

    @Override
    public String getUnary() {
        return "pow2";
    }

    @Override
    public int makeSomethingInt(int x) {
        if (x < 0) {
            throw new BinaryOperationException("exponent cannot be negative");
        }
        if (x >= 32) {
            throw new BinaryOperationException("result is too big for int");
        }
        return 1 << x;
    }
}
