package expression.operations;

public class IntegerOperation implements Operation<Integer> {
    @Override
    public Integer add(Integer x, Integer y) throws NumberException {
        int INT_MAX = Integer.MAX_VALUE;
        int INT_MIN = Integer.MIN_VALUE;
        if (((y > 0) && (x > (INT_MAX - y))) || ((y < 0) && (x < (INT_MIN - y)))) {
            throw new NumberException("result of sum is too big for int");
        } else {
            return x + y;
        }
    }

    @Override
    public Integer sub(Integer x, Integer y) throws NumberException {
        int INT_MAX = (1 << 31) - 1;
        int INT_MIN = -1 * (1 << 31);
        if ((y > 0 && x < INT_MIN + y) || (y < 0 && x > INT_MAX + y)) {
            throw new NumberException("result of subtract is too big for int");
        } else {
            return x - y;
        }
    }

    @Override
    public Integer mul(Integer x, Integer y) throws NumberException {
        int INT_MAX = (1 << 31) - 1;
        int INT_MIN = -1 * (1 << 31);
        if (x > 0) {
            if (y > 0) {
                if (x > (INT_MAX / y)) {
                    throw new NumberException("result of mul is too big for int");
                }
            } else {
                if (y < (INT_MIN / x)) {
                    throw new NumberException("result of mul is too big for int");
                }
            }
        } else {
            if (y > 0) {
                if (x < (INT_MIN / y)) {
                    throw new NumberException("result of mul is too big for int");
                }
            } else {
                if ((x != 0) && (y < (INT_MAX / x))) {
                    throw new NumberException("result of mul is too big for int");
                }
            }
        }

        return x * y;
    }

    @Override
    public Integer div(Integer x, Integer y) throws NumberException {
        if (y == 0) {
            throw new NumberException("divide by 0");
        }
        if (y == -1 && x <= -(1 << 31)) {
            throw new NumberException("result of divide is too big for int");
        }
        return x / y;
    }

    @Override
    public Integer neg(Integer x) throws NumberException {
        if (x != Integer.MIN_VALUE) {
            return -x;
        }
        throw new NumberException("result of negate is too big for int");
    }

    @Override
    public Integer parseNumber(String s) throws NumberException {
        return Integer.parseInt(s);
    }
}
