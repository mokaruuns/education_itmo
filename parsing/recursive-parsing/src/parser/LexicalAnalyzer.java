import java.io.InputStream;
import java.text.ParseException;

public class LexicalAnalyzer {

    InputStream is;
    int lastChar;
    int currentChar;
    int currentPos;
    Token currentToken;

    public LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        currentPos = 0;
        nextChar();
    }

    private void nextChar() throws ParseException {
        currentPos++;
        try {
            lastChar = currentChar;
            currentChar = is.read();
        } catch (Exception e) {
            throw new ParseException("Error reading input", currentPos);
        }
    }

    private boolean isBlank(int c) {
        return c == ' ' || c == '\t' || c == '\r';
    }

    private boolean isLetter(int c) {
        return 'a' <= c && c <= 'z';
    }

    private void skipBlank() throws ParseException {
        while (isBlank(currentChar)) {
            nextChar();
        }
    }

    public void nextToken() throws ParseException {
        skipBlank();
        switch (currentChar) {
            case -1 -> currentToken = Token.T_END;
            case '*' -> {
                nextChar();
                currentToken = Token.T_STAR;
            }
            case '|' -> {
                nextChar();
                currentToken = Token.T_OR;
            }
            case '(' -> {
                nextChar();
                currentToken = Token.T_OPEN;
            }
            case ')' -> {
                nextChar();
                currentToken = Token.T_CLOSE;
            }
            default -> {
                if (isLetter(currentChar)) {
                    nextChar();
                    currentToken = Token.T_CHAR;
                } else {
                    throw new ParseException("Illegal character", currentPos);
                }
            }
        }
    }

    public Token currentToken() {
        return currentToken;
    }

    public int currentPos() {
        return currentPos;
    }

    public char currentChar() {
        return (char) currentChar;
    }

}

