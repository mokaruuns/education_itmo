import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import classes.Tree;
import classes.Nodes;

public class Main {
    public static void main(String[] args) {
        testParse();

    }

    private static void testParse() {
        Path path = Paths.get("src/main/java/resources/test.tex");
        try {
            GrammarLexer lexer = new GrammarLexer(CharStreams.fromPath(path));
            GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));
            Tree tree = parser.math().tree_;
            System.out.println(tree.toMathML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    private static void testToMathMl(){
//        Tree tree = new Tree(Nodes.MATH,
//                new Tree(Nodes.MROW,
//                        new Tree(Nodes.MI, "a"),
//                        new Tree(Nodes.MFRAC, Lis
//                                new Tree(Nodes.MI, "b"),
//                                new Tree(Nodes.MI, "c")
//                        )
//                )
//        );
//        System.out.println(tree.toMathML());
//    }
}