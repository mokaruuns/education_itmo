import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    LexicalAnalyzer lex;

    public Tree parse(LexicalAnalyzer lex) throws ParseException {
        this.lex = lex;
        lex.nextToken();
        Tree tree = Or();
        if (lex.currentToken != Token.T_END) {
            throw new ParseException("Unexpected token", lex.currentPos);
        }
        return tree;
    }

    private Tree Or() throws ParseException {
        Tree tree = new Tree("Or");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                tree.addChild(And());
                tree.addChild(Or1());
                assert lex.currentToken != Token.T_END && lex.currentToken != Token.T_CLOSE;
            }
            default -> {
                throw new ParseException("Unexpected token", lex.currentPos);
            }
        }
        return tree;
    }

    private Tree Or1() throws ParseException {
        Tree tree = new Tree("Or1");
        switch (lex.currentToken) {
            case T_OR -> {
                lex.nextToken();
                tree.addChild(And());
                tree.addChild(Or1());
                assert lex.currentToken != Token.T_END && lex.currentToken != Token.T_CLOSE;
            }
            case T_END, T_CLOSE -> {
//                tree.addChild(new Tree("eps"));
            }
            default -> {
                throw new ParseException("Unexpected token", lex.currentPos);
            }
        }
        return tree;
    }

    private Tree And() throws ParseException {
        Tree tree = new Tree("And");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                tree.addChild(Star());
                tree.addChild(And1());
                assert lex.currentToken != Token.T_END && lex.currentToken != Token.T_CLOSE && lex.currentToken != Token.T_OR;
            }
            default -> {
                throw new ParseException("Unexpected token", lex.currentPos);
            }
        }
        return tree;
    }

    private Tree And1() throws ParseException {
        Tree tree = new Tree("And1");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                tree.addChild(Star());
                tree.addChild(And1());
                assert lex.currentToken != Token.T_END && lex.currentToken != Token.T_CLOSE && lex.currentToken != Token.T_OR;
            }
            case T_OR, T_CLOSE, T_END -> {
//                tree.addChild(new Tree("eps"));
            }
            default -> {
                throw new ParseException("Unexpected token", lex.currentPos);
            }
        }
        return tree;
    }

    private Tree Star() throws ParseException {
        Tree tree = new Tree("Star");
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                tree.addChild(Base());
                tree.addChild(Star1());
                assert lex.currentToken != Token.T_END && lex.currentToken != Token.T_CLOSE && lex.currentToken != Token.T_OR && lex.currentToken != Token.T_CHAR;
            }
            default -> {
                throw new ParseException("Unexpected token", lex.currentPos);
            }
        }
        return tree;
    }

    private Tree Star1() throws ParseException {
        Tree tree = new Tree("Star1");
        switch (lex.currentToken) {
            case T_STAR -> {
                lex.nextToken();
                tree.addChild(new Tree("*"));
                assert lex.currentToken != Token.T_END && lex.currentToken != Token.T_CLOSE && lex.currentToken != Token.T_OR && lex.currentToken != Token.T_CHAR;
            }
            default -> {
//                tree.addChild(new Tree("eps"));
            }
        }
        return tree;
    }

    private Tree Base() throws ParseException {
        Tree tree = new Tree("Base");
        switch (lex.currentToken) {
            case T_CHAR -> {
                tree.addChild(new Tree(String.valueOf((char)lex.lastChar)));
                lex.nextToken();
            }
            case T_OPEN -> {
                lex.nextToken();
                tree.addChild(new Tree("("));
                tree.addChild(Or());
                if (lex.currentToken != Token.T_CLOSE) {
                    throw new ParseException("Unexpected token", lex.currentPos);
                }
                tree.addChild(new Tree(")"));
                lex.nextToken();
            }
        }
        return tree;
    }


}