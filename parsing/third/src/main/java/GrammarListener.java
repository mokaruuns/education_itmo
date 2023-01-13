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
	 * Enter a parse tree produced by {@link GrammarParser#msqrt}.
	 * @param ctx the parse tree
	 */
	void enterMsqrt(GrammarParser.MsqrtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#msqrt}.
	 * @param ctx the parse tree
	 */
	void exitMsqrt(GrammarParser.MsqrtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#brace}.
	 * @param ctx the parse tree
	 */
	void enterBrace(GrammarParser.BraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#brace}.
	 * @param ctx the parse tree
	 */
	void exitBrace(GrammarParser.BraceContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#square}.
	 * @param ctx the parse tree
	 */
	void enterSquare(GrammarParser.SquareContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#square}.
	 * @param ctx the parse tree
	 */
	void exitSquare(GrammarParser.SquareContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#paren}.
	 * @param ctx the parse tree
	 */
	void enterParen(GrammarParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#paren}.
	 * @param ctx the parse tree
	 */
	void exitParen(GrammarParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#mfrac}.
	 * @param ctx the parse tree
	 */
	void enterMfrac(GrammarParser.MfracContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mfrac}.
	 * @param ctx the parse tree
	 */
	void exitMfrac(GrammarParser.MfracContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#mn}.
	 * @param ctx the parse tree
	 */
	void enterMn(GrammarParser.MnContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mn}.
	 * @param ctx the parse tree
	 */
	void exitMn(GrammarParser.MnContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#mo}.
	 * @param ctx the parse tree
	 */
	void enterMo(GrammarParser.MoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mo}.
	 * @param ctx the parse tree
	 */
	void exitMo(GrammarParser.MoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#ms}.
	 * @param ctx the parse tree
	 */
	void enterMs(GrammarParser.MsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#ms}.
	 * @param ctx the parse tree
	 */
	void exitMs(GrammarParser.MsContext ctx);
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