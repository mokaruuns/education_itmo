package expression.exceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ParserException extends RuntimeException {
    public ParserException(final String message) {
        super(message);
    }
}
