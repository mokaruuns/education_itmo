package test;

import org.junit.Assert;
import org.junit.Test;
import parser.LexicalAnalyzer;
import parser.Parser;
import parser.Tree;

import java.text.ParseException;

public class ParserTest {

    @Test
    public void testBase() throws ParseException {
        Tree actual = getTree("a");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1")
                        ),
                        new Tree("And1")
                ),
                new Tree("Or1")
        );
        assertTree(expected, actual);
    }

    @Test
    public void testBaseBracket() throws ParseException {
        String regex = "(a)";
        LexicalAnalyzer lex = new LexicalAnalyzer(regex);
        Parser parser = new Parser();
        Tree actual = parser.parse(lex);
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("("),
                                        getTree("a"),
                                        new Tree(")")
                                ),
                                new Tree("Star1")
                        ),
                        new Tree("And1")),
                new Tree("Or1")
        );
        assertTree(expected, actual);
    }

    @Test
    public void testOr() throws ParseException {
        Tree actual = getTree("a|b");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1")
                        ),
                        new Tree("And1")
                ),
                new Tree("Or1",
                        new Tree("And",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("b")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        ),
                        new Tree("Or1")
                )
        );
        assertTree(expected, actual);
    }

    @Test
    public void testAnd() throws ParseException {
        Tree actual = getTree("ab");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1")
                        ),
                        new Tree("And1",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("b")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        )
                ),
                new Tree("Or1")
        );
        assertTree(expected, actual);
    }

    @Test
    public void testStar() throws ParseException {
        Tree actual = getTree("a*");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1",
                                        new Tree("*")
                                )
                        ),
                        new Tree("And1")
                ),
                new Tree("Or1")
        );
        assertTree(expected, actual);
    }

    @Test
    public void testStarAnd() throws ParseException {
        Tree actual = getTree("a*b");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1",
                                        new Tree("*")
                                )
                        ),
                        new Tree("And1",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("b")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        )
                ),
                new Tree("Or1")
        );
        assertTree(expected, actual);
    }

    @Test
    public void testStarOr() throws ParseException {
        Tree actual = getTree("a*b|c");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1",
                                        new Tree("*")
                                )
                        ),
                        new Tree("And1",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("b")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        )
                ),
                new Tree("Or1",
                        new Tree("And",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("c")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        ),
                        new Tree("Or1")
                )
        );
        assertTree(expected, actual);
    }

    @Test
    public void testStarOr2() throws ParseException {
        Tree actual = getTree("a*|b");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1",
                                        new Tree("*")
                                )
                        ),
                        new Tree("And1")
                ),
                new Tree("Or1",
                        new Tree("And",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("b")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        ),
                        new Tree("Or1")
                )
        );
        assertTree(expected, actual);
    }

    @Test
    public void testOrBracket() throws ParseException {
        Tree actual = getTree("a|b|(c|d)");
        Tree expected = new Tree("Or",
                new Tree("And",
                        new Tree("Star",
                                new Tree("Base",
                                        new Tree("a")),
                                new Tree("Star1")
                        ),
                        new Tree("And1")
                ),
                new Tree("Or1",
                        new Tree("And",
                                new Tree("Star",
                                        new Tree("Base",
                                                new Tree("b")),
                                        new Tree("Star1")
                                ),
                                new Tree("And1")
                        ),
                        new Tree("Or1",
                                new Tree("And",
                                        new Tree("Star",
                                                new Tree("Base",
                                                        new Tree("("),
                                                        getTree("c|d"),
                                                        new Tree(")")
                                                ),
                                                new Tree("Star1")
                                        ),
                                        new Tree("And1")
                                ),
                                new Tree("Or1")
                        )
                )
        );
    }

    private void assertTree(Tree expected, Tree actual) {

        Assert.assertEquals(expected.getNode(), actual.getNode());
        Assert.assertEquals(expected.getChildren().size(), actual.getChildren().size());
        for (int i = 0; i < expected.getChildren().size(); i++) {
            assertTree(expected.getChildren().get(i), actual.getChildren().get(i));
        }
    }

    private Tree getTree(String regex) throws ParseException {
        LexicalAnalyzer lex = new LexicalAnalyzer(regex);
        Parser parser = new Parser();
        return parser.parse(lex);
    }

}
