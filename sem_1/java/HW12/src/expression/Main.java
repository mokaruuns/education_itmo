package expression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        String s = "x / y / z";
        System.out.println(parser.parse(s));
        System.out.println(parser.parse(s).evaluate(0, 1, 2));
    }
}
