package expression.operations;

public interface Operation<T> {
    T add(T x, T y) throws NumberException;

    T sub(T x, T y) throws NumberException;

    T mul(T x, T y) throws NumberException;

    T div(T x, T y) throws NumberException;

    T neg(T x) throws NumberException;

    T parseNumber(String s) throws NumberException;
}
