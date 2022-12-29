// Generated from java-escape by ANTLR 4.11.1

    import classes.Tree;
    import classes.Nodes;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#math}.
	 * @param ctx the parse tree
	 */
	void enterMath(GrammarParser.MathContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#math}.
	 * @param ctx the parse tree
	 */
	void exitMath(GrammarParser.MathContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#mrow}.
	 * @param ctx the parse tree
	 */
	void enterMrow(GrammarParser.MrowContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mrow}.
	 * @param ctx the parse tree
	 */
	void exitMrow(GrammarParser.MrowContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#mi}.
	 * @param ctx the parse tree
	 */
	void enterMi(GrammarParser.MiContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mi}.
	 * @param ctx the parse tree
	 */
	void exitMi(GrammarParser.MiContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#greek}.
	 * @param ctx the parse tree
	 */
	void enterGreek(GrammarParser.GreekContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#greek}.
	 * @param ctx the parse tree
	 */
	void exitGreek(GrammarParser.GreekContext ctx);
}