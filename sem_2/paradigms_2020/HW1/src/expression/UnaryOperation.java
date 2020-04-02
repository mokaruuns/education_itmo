package expression;

import java.util.Objects;

public class UnaryOperation implements CommonExpression {
    private CommonExpression first;

    UnaryOperation(CommonExpression first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "(" + getUnary() + first + ")";
    }

    public String getUnary() {
        return "";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        UnaryOperation const_a = (UnaryOperation) object;
        return const_a.first == first;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeSomethingInt(first.evaluate(x, y, z));
    }

    public int evaluate(int x) {
        return makeSomethingInt(first.evaluate(x));
    }

    public int makeSomethingInt(int x) {
        return 0;
    }
}
