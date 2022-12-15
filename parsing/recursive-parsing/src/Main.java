import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Parsing...");
        testParser("a|b|c*d|e(f|g)");
        System.out.println("Parsing done.");
    }

    public static void testParser(String initialString) throws ParseException {

        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        Parser parser = new Parser();
        LexicalAnalyzer lex = new LexicalAnalyzer(targetStream);
        Tree tree = null;
        try {
            tree = parser.parse(lex);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Graph graph = new Graph(tree);
        graph.build();
        graph.toDotFile("graph.gv");
    }
}