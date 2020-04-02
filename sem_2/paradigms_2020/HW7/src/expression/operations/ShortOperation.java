package expression.operations;

public class ShortOperation implements Operation<Short> {

    @Override
    public Short add(Short x, Short y) throws NumberException {
        return (short) (x + y);
    }

    @Override
    public Short sub(Short x, Short y) throws NumberException {
        return (short) (x - y);
    }

    @Override
    public Short mul(Short x, Short y) throws NumberException {
        return (short) (x * y);
    }

    @Override
    public Short div(Short x, Short y) throws NumberException {
        if (y == 0) {
            throw new NumberException("divide by zero");
        }
        return (short) (x / y);
    }

    @Override
    public Short neg(Short x) throws NumberException {
        return (short) (-x);
    }

    @Override
    public Short parseNumber(String s) throws NumberException {
        try {
            return (short) Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumberException("Wrong Short number: " + s);
        }
    }
}
