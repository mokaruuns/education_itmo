package expression;


public class CheckedSubtract extends BinaryOperations {
    public CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " - ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        int INT_MAX = (1 << 31) - 1;
        int INT_MIN = -1 * (1 << 31);
        if ((y > 0 && x < INT_MIN + y) || (y < 0 && x > INT_MAX + y)) {
            throw new BinaryOperationException("result of subtract is too big for int");
        } else {
            return x - y;
        }
    }

}
