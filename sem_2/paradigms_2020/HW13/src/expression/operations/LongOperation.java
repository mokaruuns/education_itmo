package expression.operations;

public class LongOperation implements Operation<Long> {
    @Override
    public Long add(Long x, Long y) throws NumberException {
        return x + y;
    }

    @Override
    public Long sub(Long x, Long y) throws NumberException {
        return x - y;
    }

    @Override
    public Long mul(Long x, Long y) throws NumberException {
        return x * y;
    }

    @Override
    public Long div(Long x, Long y) throws NumberException {
        if (y == 0) {
            throw new NumberException("divide by zero");
        }
        return x / y;
    }

    @Override
    public Long neg(Long x) throws NumberException {
        return -x;
    }

    @Override
    public Long parseNumber(String s) throws NumberException {
        return Long.parseLong(s);
    }
}
