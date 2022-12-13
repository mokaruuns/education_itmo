import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    LexicalAnalyzer lex;

    public Tree parse(LexicalAnalyzer lex) throws ParseException {
        this.lex = lex;
        lex.nextToken();
        Tree tree = E();
        if (lex.currentToken != Token.T_END) {
            throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }


    private Tree E() throws ParseException {
        Tree tree = new Tree("E");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                Tree a = A();
                Tree e1 = E1();
                if (lex.currentToken == Token.T_END || lex.currentToken == Token.T_CLOSE) {
                    tree.addChild(a);
                    tree.addChild(e1);

                } else {
                    throw new ParseException("Unexpected token", lex.currentPos);
                }
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }

    private Tree A() throws ParseException {
        Tree tree = new Tree("A");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                Tree s = S();
                Tree a1 = A1();
                if (lex.currentToken == Token.T_END || lex.currentToken == Token.T_CLOSE || lex.currentToken == Token.T_OR) {
                    tree.addChild(s);
                    tree.addChild(a1);

                } else {
                    throw new ParseException("Unexpected token", lex.currentPos);
                }
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }

    private Tree A1() throws ParseException {
        Tree tree = new Tree("A1");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                Tree s = S();
                Tree a1 = A1();
                if (lex.currentToken == Token.T_END || lex.currentToken == Token.T_CLOSE || lex.currentToken == Token.T_OR) {
                    tree.addChild(s);
                    tree.addChild(a1);

                } else {
                    throw new ParseException("Unexpected token", lex.currentPos);
                }
            }
            case T_CLOSE, T_END, T_OR -> {
                return null;
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }

    private Tree S() throws ParseException {
        Tree tree = new Tree("S");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                Tree k = K();
                lex.nextToken();

                if (lex.currentToken == Token.T_STAR) {
                    tree.setNode("S*");
                    lex.nextToken();
                }
                tree.addChild(k);
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }

    private Tree K() throws ParseException {
        Tree tree = new Tree("K");
        switch (lex.currentToken) {
            case T_CHAR -> {
                return new Tree("K", new Tree(String.valueOf((char) lex.lastChar)));
            }
            case T_OPEN -> {
                lex.nextToken();
                Tree e = E();
                if (lex.currentToken == Token.T_CLOSE) {
                    lex.nextToken();
                    tree.addChild(e);
                } else {
                    throw new ParseException("Unexpected token", lex.currentPos);
                }
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }


    private Tree E1() throws ParseException {
        Tree tree = new Tree("E1");
        switch (lex.currentToken) {
            case T_OR -> {
                lex.nextToken();
                Tree a = A();
                Tree e1 = E1();
                tree.addChild(a);
                tree.addChild(e1);

            }
            case T_END, T_CLOSE -> {
                return null;
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }
}
