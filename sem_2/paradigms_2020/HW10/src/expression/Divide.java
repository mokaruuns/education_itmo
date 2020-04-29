package expression;

import expression.operations.NumberException;
import expression.operations.Operation;

public class Divide<T> extends AbstractOperator<T> {
    public Divide(TripleExpression<T> first, TripleExpression<T> second, Operation<T> operation) {
        super(first, second, operation);
    }

    public T operator(T a, T b) throws NumberException {
        return operation.div(a, b);
    }
}
