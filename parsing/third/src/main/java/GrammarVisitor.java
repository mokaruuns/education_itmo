// Generated from java-escape by ANTLR 4.11.1

    import classes.Tree;
    import classes.Nodes;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#math}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath(GrammarParser.MathContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#mrow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMrow(GrammarParser.MrowContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#msqrt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMsqrt(GrammarParser.MsqrtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#brace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrace(GrammarParser.BraceContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#square}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquare(GrammarParser.SquareContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#paren}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(GrammarParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#mfrac}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMfrac(GrammarParser.MfracContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#mi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMi(GrammarParser.MiContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#mn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMn(GrammarParser.MnContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#mo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMo(GrammarParser.MoContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#ms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMs(GrammarParser.MsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#greek}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreek(GrammarParser.GreekContext ctx);
}