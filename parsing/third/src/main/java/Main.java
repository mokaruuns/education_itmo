import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        testParse();

    }

    private static void testParse() {
        Path path = Paths.get("src/main/java/resources/test.tex");
        try {
            GrammarLexer lexer = new GrammarLexer(CharStreams.fromPath(path));
            GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.math();
            Visitor visitor = new Visitor();
            String result = visitor.visit(tree);
            toHtml(result);
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void toHtml (String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.html"));){
            writer.write("<!DOCTYPE html>\r");
            writer.write("<html  lang=\"en\">\r");
            writer.write("<head>\r");
            writer.write("<meta charset=\"utf-8\">\r");
            writer.write("<title>Result</title>\r");
            writer.write("</head>\r");
            writer.write("<body>\r");
            writer.write(result);
            writer.write("</body>\r");
            writer.write("</html>\r");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}