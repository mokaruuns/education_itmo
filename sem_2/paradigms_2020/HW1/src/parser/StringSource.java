package parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info) and Marsel
 */
public class StringSource implements Source {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }


    @Override
    public ParserException errorWithPos(final String message) {
        return new ParserException(message + " in position(" + pos + ") in expression: \"" + data + "\"");
    }

    @Override
    public ParserException errorWithoutPos(final String message) {
        return new ParserException(message + " in expression: \"" + data + "\"");
    }
}
