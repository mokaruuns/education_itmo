package expression;

public class Variable implements CommonExpression {
    private String string;

    public Variable(String x) {
        this.string = x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (string) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
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

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return null;
    }
}
