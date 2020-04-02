package expression;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    LEFT_SHIFT("<<"),
    RIGHT_SHIFT(">>"),
    VARIABLE(null),
    DIGIT(null),
    WHITESPACE(null),
    END_LINE("\0"),
    BRACKET_LEFT("("),
    BRACKET_RIGHT(")");
    private String operate;

    Operator(String operator) {
        this.operate = operator;
    }

    public String getStringOperator() {
        return operate;
    }

    public int getLength() {
        if (operate != null) {
            return getStringOperator().length();
        } else
            return 1;
    }
}