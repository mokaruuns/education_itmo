package expression;

public class CheckedLog extends UnaryOperation {

    public CheckedLog(CommonExpression first) {
        super(first);
    }

    @Override
    public String getUnary() {
        return "log2";
    }

    @Override
    public int makeSomethingInt(int x) {
        if (x <= 0) {
            throw new BinaryOperationException("number cannot be negative");
        }
        int res = 0;
        while (x > 1) {
            res++;
            x /= 2;
        }
        return res;
    }
}