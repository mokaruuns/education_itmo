// Generated from java-escape by ANTLR 4.11.1
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
	 * Enter a parse tree produced by {@link GrammarParser#indexes}.
	 * @param ctx the parse tree
	 */
	void enterIndexes(GrammarParser.IndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#indexes}.
	 * @param ctx the parse tree
	 */
	void exitIndexes(GrammarParser.IndexesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#msup}.
	 * @param ctx the parse tree
	 */
	void enterMsup(GrammarParser.MsupContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#msup}.
	 * @param ctx the parse tree
	 */
	void exitMsup(GrammarParser.MsupContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#msub}.
	 * @param ctx the parse tree
	 */
	void enterMsub(GrammarParser.MsubContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#msub}.
	 * @param ctx the parse tree
	 */
	void exitMsub(GrammarParser.MsubContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#actions}.
	 * @param ctx the parse tree
	 */
	void enterActions(GrammarParser.ActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#actions}.
	 * @param ctx the parse tree
	 */
	void exitActions(GrammarParser.ActionsContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#mbinom}.
	 * @param ctx the parse tree
	 */
	void enterMbinom(GrammarParser.MbinomContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mbinom}.
	 * @param ctx the parse tree
	 */
	void exitMbinom(GrammarParser.MbinomContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link GrammarParser#functions}.
	 * @param ctx the parse tree
	 */
	void enterFunctions(GrammarParser.FunctionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#functions}.
	 * @param ctx the parse tree
	 */
	void exitFunctions(GrammarParser.FunctionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(GrammarParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(GrammarParser.IdentifierContext ctx);
}