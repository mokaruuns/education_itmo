package parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info) and Marsel
 */
public interface Source {
    boolean hasNext();

    char next();

    ParserException errorWithPos(final String message);

    ParserException errorWithoutPos(final String message);
}
