package expression.operations;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {

    @Override
    public BigInteger add(BigInteger x, BigInteger y) throws NumberException {
        return x.add(y);
    }

    @Override
    public BigInteger sub(BigInteger x, BigInteger y) throws NumberException {
        return x.subtract(y);
    }

    @Override
    public BigInteger mul(BigInteger x, BigInteger y) throws NumberException {
        return x.multiply(y);
    }

    @Override
    public BigInteger div(BigInteger x, BigInteger y) throws NumberException {
        if (y.equals(BigInteger.ZERO)) {
            throw new NumberException("divide by zero");
        }
        return x.divide(y);
    }

    @Override
    public BigInteger neg(BigInteger x) throws NumberException {
        return x.negate();
    }

    @Override
    public BigInteger parseNumber(String s) throws NumberException {
        return new BigInteger(s);
    }
}
