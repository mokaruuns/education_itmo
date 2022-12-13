import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Hello world!");

        testParser("a(nk*)|m|k");
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
        graph.toDot();
        graph.toDotFile("graph.gv");
    }
}