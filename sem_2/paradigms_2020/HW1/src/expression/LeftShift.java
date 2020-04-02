package expression;

public class LeftShift extends BinaryOperations {

    public LeftShift(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " << ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        return x << y;
    }


}
