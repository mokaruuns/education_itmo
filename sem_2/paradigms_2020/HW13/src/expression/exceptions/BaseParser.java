package expression.exceptions;


/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info) and Marsel
 */
public class BaseParser {
    private final StringSource source;
    protected char ch;

    protected BaseParser(final StringSource source) {
        this.source = source;
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }


    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected String getMessage(String expected) {
        return "Expected \"" + expected + "\", found '" + ch + "'";
    }

    protected String getMessage(char expected) {
        return "Expected '" + expected + "', found '" + ch + "'";
    }

    protected void expect(final char c) {
        if (ch != c) {
            throw errorWithPos(getMessage(c));
        }
        nextChar();
    }


    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected ParserException errorWithPos(final String message) {
        return source.errorWithPos(message);
    }

    protected ParserException errorWithoutPos(final String message) {
        return source.errorWithoutPos(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
