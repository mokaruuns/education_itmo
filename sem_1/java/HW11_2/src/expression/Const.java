package expression;

public class Const implements TwoExpressions {
    private boolean isInt = true;
    private double number_double;

    Const(int i) {
        this.number_double = i;
    }

    Const(double d) {
        this.number_double = d;
        isInt = false;
    }

    @Override
    public double evaluate(double x) {
        return number_double;
    }

    @Override
    public int evaluate(int x) {
        return (int) number_double;
    }

    @Override
    public String toString() {
        if (isInt) {
            return Integer.toString((int) number_double);
        } else {
            return Double.toString(number_double);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Const const_a = (Const) object;
        return const_a.number_double == number_double;

    }

    @Override
    public int hashCode() {
        return Double.hashCode(number_double);
    }
}
