package expression;

public class RightShift extends BinaryOperations {

    public RightShift(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " >> ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        return x >> y;
    }


}
