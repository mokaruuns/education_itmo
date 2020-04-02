package expression;

public class Divide extends BinaryOperations {
    Divide(TwoExpressions first, TwoExpressions second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " / ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        return x / y;
    }

    @Override
    public double makeSomethingDouble(double x, double y) {
        return x / y;
    }
}
