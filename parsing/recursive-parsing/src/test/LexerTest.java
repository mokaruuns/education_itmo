package test;

import org.junit.Assert;
import parser.LexicalAnalyzer;
import org.junit.Test;
import parser.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class LexerTester {


    @Test
    public void testChar() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_END
        ));
        assertEquals("a", expected);
    }

    @Test
    public void testStar() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_STAR, Token.T_END
        ));
        assertEquals("a*", expected);
    }


    @Test
    public void testOr() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_OR, Token.T_CHAR, Token.T_END
        ));
        assertEquals("a|b", expected);
    }

    @Test
    public void testOrStar() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_OR, Token.T_CHAR, Token.T_STAR, Token.T_END
        ));
        assertEquals("a|b*", expected);
    }

    @Test

    public void testOrStar2() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_STAR, Token.T_OR, Token.T_CHAR, Token.T_STAR, Token.T_END
        ));
        assertEquals("a*|b*", expected);
    }

    @Test
    public void testAnd() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_CHAR, Token.T_END
        ));
        assertEquals("ab", expected);
    }

    @Test
    public void testAndStar() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_CHAR, Token.T_CHAR, Token.T_STAR, Token.T_END
        ));
        assertEquals("ab*", expected);
    }

    @Test
    public void testBracket() throws ParseException {
        ArrayList<Token> expected = new ArrayList<>(Arrays.asList(
                Token.T_OPEN, Token.T_CHAR, Token.T_CLOSE, Token.T_END
        ));
        assertEquals("(a)", expected);
    }

    private void assertEquals(String test, ArrayList<Token> expectedToken) throws ParseException {
        LexicalAnalyzer lex = new LexicalAnalyzer(test);
        ArrayList<Token> actualToken = lex.getTokens();
        Assert.assertArrayEquals(new ArrayList[]{actualToken}, new ArrayList[]{expectedToken});
    }
}
