package clojure.cljtest.functional;



import clojure.cljtest.ClojureEngine;
import clojure.cljtest.multi.MultiTests;
import javascript.jstest.AbstractTests;
import javascript.jstest.BaseJavascriptTest;
import javascript.jstest.Language;

import java.util.Optional;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ClojureFunctionalExpressionTest extends BaseJavascriptTest<ClojureEngine> {
    public static final Dialect PARSED = dialect(
            "(variable \"%s\")",
            "(constant %s.0)",
            (op, args) -> "(" + op + " " + String.join(" ", args) + ")"
    )
            .rename("+", "add")
            .rename("-", "subtract")
            .rename("*", "multiply")
            .rename("/", "divide");

    public static final Dialect UNPARSED = dialect(
            "%s",
            "%s.0",
            (op, args) -> "("+ op + " " + String.join(" ", args) + ")"
    );

    protected ClojureFunctionalExpressionTest(final Language language, final Optional<String> evaluate) {
        super(new ClojureEngine("/home/mokaruuns/git/education_itmo/sem_2/paradigms_2020/HW11/src/clojure/cljtest/object/expression.clj", evaluate), language, true);
    }

    protected ClojureFunctionalExpressionTest(final AbstractTests tests) {
        this(new Language(PARSED, UNPARSED, tests), Optional.empty());
    }

    @Override
    protected String parse(final String expression) {
        return "(parseFunction \"" + expression + "\")";
    }

    public static void main(final String... args) {
        new ClojureFunctionalExpressionTest(new MultiTests(mode(args, ClojureFunctionalExpressionTest.class))).run();
    }

    protected static boolean mode(final String[] args, final Class<?> type) {
        return mode(args, type, "easy", "hard") == 1;
    }
}
