package expression;

public class CheckedMultiply extends BinaryOperations {
    public CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " * ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        int INT_MAX = (1 << 31) - 1;
        int INT_MIN = -1 * (1 << 31);
        if (x > 0) {
            if (y > 0) {
                if (x > (INT_MAX / y)) {
                    throw new BinaryOperationException("result of mul is too big for int");
                }
            } else {
                if (y < (INT_MIN / x)) {
                    throw new BinaryOperationException("result of mul is too big for int");
                }
            }
        } else {
            if (y > 0) {
                if (x < (INT_MIN / y)) {
                    throw new BinaryOperationException("result of mul is too big for int");
                }
            } else {
                if ((x != 0) && (y < (INT_MAX / x))) {
                    throw new BinaryOperationException("result of mul is too big for int");
                }
            }
        }

        return x * y;
    }
}