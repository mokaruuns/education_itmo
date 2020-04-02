package expression;

import java.util.Objects;

public class BinaryOperations implements CommonExpression {
    private CommonExpression first, second;

    BinaryOperations(CommonExpression first, CommonExpression second) {
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

    protected int makeSomethingInt(int x, int y) {
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

    @Override
    public int evaluate(int x, int y, int z) {
        return makeSomethingInt(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return makeSomethingInt(first.evaluate(x), second.evaluate(x));
    }
}
