import graph.Graph;
import parser.LexicalAnalyzer;
import parser.Parser;
import parser.Tree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Parsing...");
        testParser("a");
        testParser("(a)");
        testParser("ab");
        testParser("a|b**c");
        testParser("a**");
        testParser("a|b**");
        testParser("a|b***|c");
        testParser("a|(b*|c)*");
        testParser("a|(b*|c)*|d");
        System.out.println("Parsing done.");
    }

    public static void testParser(String initialString) throws ParseException {

        Parser parser = new Parser();
        LexicalAnalyzer lex = new LexicalAnalyzer(initialString);
        Tree tree = null;
        try {
            tree = parser.parse(lex);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Graph graph = new Graph(tree, initialString);
        graph.build();

        graph.toDotFile("dot/graph-" + initialString.hashCode()  +".gv");
    }
}