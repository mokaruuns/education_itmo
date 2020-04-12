package expression;

import expression.generic.TripleExpression;

public class Variable<T> implements TripleExpression<T> {
    private String string;

    public Variable(String x) {
        this.string = x;
    }

    public T evaluate(T x, T y, T z) {
        switch (string) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return null;
        }
    }
}
