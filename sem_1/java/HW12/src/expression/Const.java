package expression;

public class Const implements CommonExpression {
    private int number_int;

    public Const(int i) {
        this.number_int = i;
    }

    @Override
    public String toString() {
        return Integer.toString(number_int);
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
        return const_a.number_int == number_int;

    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number_int);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(x);
    }

    public int evaluate(int x) {
        return this.number_int;
    }
}
