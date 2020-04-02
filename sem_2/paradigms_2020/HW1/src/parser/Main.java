package parser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        String s = "pow23";
        System.out.println(parser.parse(s).evaluate(1, 1, 1));
        System.out.println(parser.parse(s));
    }
}
