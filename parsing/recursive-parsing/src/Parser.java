import java.text.ParseException;
import java.util.List;

public class Parser {
    LexicalAnalyzer lex;

    private Tree E() throws ParseException {
        switch (lex.currentToken) {
            case T_CHAR, T_OPEN -> {
                lex.nextToken();
                Tree E1 = E1();
                if (lex.currentToken == Token.T_END || lex.currentToken == Token.T_CLOSE) {
                    return E1;
                } else {
                    throw new ParseException("Unexpected token", lex.currentPos);
                }
            }
            default -> throw new ParseException("Unexpected token", lex.currentPos);
        }
    }

    private Tree E1() {
        // TODO: implement
        return null;
    }


}
