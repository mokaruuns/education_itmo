package expression;

public class CheckedDivide extends BinaryOperations {
    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " / ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        if (y == 0) {
            throw new BinaryOperationException("divide by 0");
        }
        if (y == -1 && x <= -(1 << 31)) {
            throw new BinaryOperationException("result of divide is too big for int");
        }
        return x / y;
    }
}
