package expression;

public class Subtract extends BinaryOperations {
    public Subtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " - ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        return x - y;
    }

}
