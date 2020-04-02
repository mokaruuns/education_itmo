package expression;

import java.util.Objects;

public class BinaryOperations implements TwoExpressions {
    private TwoExpressions first, second;

    BinaryOperations(TwoExpressions first, TwoExpressions second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + getSign() + second + ")";
    }

    public String getSign() {
        return "";
    }

    @Override
    public double evaluate(double d) {
        return makeSomethingDouble(first.evaluate(d), second.evaluate(d));
    }

    @Override
    public int evaluate(int i) {
        return makeSomethingInt(first.evaluate(i), second.evaluate(i));
    }

    protected int makeSomethingInt(int x, int y) {
        return 0;
    }

    protected double makeSomethingDouble(double x, double y) {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        BinaryOperations binaryOperations = (BinaryOperations) object;
        return binaryOperations.first.equals(this.first) && binaryOperations.second.equals(this.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second, this.getClass());
    }
}
