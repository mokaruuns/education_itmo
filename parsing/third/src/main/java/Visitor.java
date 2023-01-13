import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor extends GrammarBaseVisitor<String> {
    @Override
    public String visitMath(GrammarParser.MathContext ctx) {
        return "<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\">\n" +
                visit(ctx.getChild(1)) +
                "\n</math>";
    }

    @Override
    public String visitMrow(GrammarParser.MrowContext ctx) {
        return "<mrow>\n" +
                visitChildren(ctx) +
                "\n</mrow>";
    }


    @Override
    public String visitActions(GrammarParser.ActionsContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitMfrac(GrammarParser.MfracContext ctx) {
        return "<mfrac>\n" +
                visit(ctx.getChild(2)) +
                visit(ctx.getChild(5)) +
                "\n</mfrac>";
    }

    @Override
    public String visitMsqrt(GrammarParser.MsqrtContext ctx) {
        if (ctx.getChildCount() <= 4) {
            return "<msqrt>\n" +
                    visit(ctx.getChild(2)) +
                    "\n</msqrt>";
        } else {
            return "<mroot>\n" +
                    visit(ctx.getChild(5)) +
                    visit(ctx.getChild(2)) +
                    "\n</mroot>";
        }
    }

    @Override
    public String visitMsup(GrammarParser.MsupContext ctx) {
        return "<msup>\n" +
                visit(ctx.getChild(0)) +
                visit(ctx.getChild(3)) +
                "\n</msup>";
    }

    @Override
    public String visitMsub(GrammarParser.MsubContext ctx) {
        return "<msub>\n" +
                visit(ctx.getChild(0)) +
                visit(ctx.getChild(3)) +
                "\n</msub>";
    }

    @Override
    public String visitMo(GrammarParser.MoContext ctx) {
        return "<mo>" +
                ctx.getText() +
                "</mo>";
    }

    @Override
    public String visitMn(GrammarParser.MnContext ctx) {
        return "<mn>" +
                ctx.getText() +
                "</mn>";
    }

    @Override
    public String visitMi(GrammarParser.MiContext ctx) {
        return "<mi>" +
                visit(ctx.getChild(0)) +
                "</mi>";
    }

    @Override
    public String visitGreek(GrammarParser.GreekContext ctx) {
        String text = ctx.getText();
        return "&" + text.substring(1) + ";";
    }


    @Override
    public String visitIndexes(GrammarParser.IndexesContext ctx) {
        return visitChildren(ctx);
    }


    @Override
    public String visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public String visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        if (node.toString().equals("<EOF>")) {
            return "";
        }
        return node.toString();
    }

    @Override
    public String visitErrorNode(ErrorNode node) {
        return super.visitErrorNode(node);
    }

    @Override
    protected String defaultResult() {
        return "";
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate.isEmpty()) {
            return nextResult;
        } else {
            return aggregate + " " + nextResult;
        }
    }

//    @Override
//    protected boolean shouldVisitNextChild(RuleNode node, String currentResult) {
//        return super.shouldVisitNextChild(node, currentResult);
//    }

}
