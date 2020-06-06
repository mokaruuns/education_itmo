package clojure.cljtest.object;


import clojure.cljtest.functional.ClojureFunctionalExpressionTest;
import clojure.cljtest.multi.MultiExpLnTests;
import javascript.jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ClojureObjectExpLnTest extends ClojureObjectExpressionTest {
    public static final Dialect PARSED = ClojureObjectExpressionTest.PARSED.copy()
            .rename("exp", "Exp")
            .rename("ln", "Ln");

    protected ClojureObjectExpLnTest(final boolean testMulti) {
        super(new Language(PARSED, ClojureFunctionalExpressionTest.UNPARSED, new MultiExpLnTests(testMulti)));
    }

    public static void main(final String... args) {
        new ClojureObjectExpLnTest(mode(args, ClojureObjectExpLnTest.class)).run();
    }
}
