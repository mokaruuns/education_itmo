package expression.operations;

public class DoubleOperation implements Operation<Double> {

    @Override
    public Double add(Double x, Double y) throws NumberException {
        return x + y;
    }

    @Override
    public Double sub(Double x, Double y) throws NumberException {
        return x - y;
    }

    @Override
    public Double mul(Double x, Double y) throws NumberException {
        return x * y;
    }

    @Override
    public Double div(Double x, Double y) throws NumberException {
        return x / y;
    }

    @Override
    public Double neg(Double x) throws NumberException {
        return -x;
    }

    @Override
    public Double parseNumber(String s) throws NumberException {
        return Double.parseDouble(s);
    }
}
