package expression;

public class Variable implements TwoExpressions {
    private String string;

    Variable(String x) {
        this.string = x;
    }

    @Override
    public int evaluate(int i) {
        return i;
    }

    @Override
    public double evaluate(double d) {
        return d;
    }

    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        return object.toString().equals(this.string);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
