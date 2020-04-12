package expression;

import expression.exceptions.ParserException;
import expression.operations.NumberException;
import expression.operations.Operation;

public abstract class AbstractOperator<T> implements TripleExpression<T> {

    protected Operation<T> operation;
    private TripleExpression<T> first, second;

    public AbstractOperator(TripleExpression<T> first, TripleExpression<T> second, Operation<T> operation) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    public abstract T operator(T first, T second) throws NumberException, ParserException;

    public T evaluate(T x, T y, T z) throws NumberException, ParserException {
        return operator(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

}