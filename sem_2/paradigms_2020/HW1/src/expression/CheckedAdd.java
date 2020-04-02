package expression;


public class CheckedAdd extends BinaryOperations {

    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " + ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        int INT_MAX = Integer.MAX_VALUE;
        int INT_MIN = Integer.MIN_VALUE;
        if (((y > 0) && (x > (INT_MAX - y))) || ((y < 0) && (x < (INT_MIN - y)))) {
            throw new BinaryOperationException("result of sum is too big for int");
        } else {
            return x + y;
        }
    }
}
