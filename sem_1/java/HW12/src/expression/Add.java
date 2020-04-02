package expression;

public class Add extends BinaryOperations {

    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public String getSign() {
        return " + ";
    }

    @Override
    public int makeSomethingInt(int x, int y) {
        return x + y;
    }


}
