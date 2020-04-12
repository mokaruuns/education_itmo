package expression;

import expression.operations.NumberException;
import expression.operations.Operation;

public class Negate<T> extends AbstractOperator<T> {

    public Negate(TripleExpression<T> first, Operation<T> operation) {
        super(first, first, operation);
    }

    public T operator(T a, T b) throws NumberException {
        return operation.neg(a);
    }
}


