// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, GREEK=12, FUNCTION=13, IDENTIFIER=14, NUMBER=15, WS=16, 
		SYMBOL=17, OPERATOR=18, LETTER=19;
	public static final int
		RULE_math = 0, RULE_mrow = 1, RULE_indexes = 2, RULE_msup = 3, RULE_msub = 4, 
		RULE_actions = 5, RULE_mfrac = 6, RULE_msqrt = 7, RULE_mbinom = 8, RULE_mi = 9, 
		RULE_mn = 10, RULE_mo = 11, RULE_ms = 12, RULE_greek = 13, RULE_functions = 14, 
		RULE_identifier = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"math", "mrow", "indexes", "msup", "msub", "actions", "mfrac", "msqrt", 
			"mbinom", "mi", "mn", "mo", "ms", "greek", "functions", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'$'", "'^'", "'{'", "'}'", "'_'", "'\\frac'", "'\\sqrt'", "'['", 
			"']'", "'\\binom'", "'\\'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"GREEK", "FUNCTION", "IDENTIFIER", "NUMBER", "WS", "SYMBOL", "OPERATOR", 
			"LETTER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MathContext extends ParserRuleContext {
		public MrowContext mrow() {
			return getRuleContext(MrowContext.class,0);
		}
		public MathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_math; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathContext math() throws RecognitionException {
		MathContext _localctx = new MathContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_math);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__0);
			setState(33);
			mrow();
			setState(34);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MrowContext extends ParserRuleContext {
		public List<IndexesContext> indexes() {
			return getRuleContexts(IndexesContext.class);
		}
		public IndexesContext indexes(int i) {
			return getRuleContext(IndexesContext.class,i);
		}
		public List<ActionsContext> actions() {
			return getRuleContexts(ActionsContext.class);
		}
		public ActionsContext actions(int i) {
			return getRuleContext(ActionsContext.class,i);
		}
		public MrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mrow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMrow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMrow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMrow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MrowContext mrow() throws RecognitionException {
		MrowContext _localctx = new MrowContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(38);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(36);
					indexes();
					}
					break;
				case 2:
					{
					setState(37);
					actions();
					}
					break;
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 445632L) != 0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexesContext extends ParserRuleContext {
		public MsubContext msub() {
			return getRuleContext(MsubContext.class,0);
		}
		public MsupContext msup() {
			return getRuleContext(MsupContext.class,0);
		}
		public IndexesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterIndexes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitIndexes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitIndexes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexesContext indexes() throws RecognitionException {
		IndexesContext _localctx = new IndexesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_indexes);
		try {
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				msub();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				msup();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MsupContext extends ParserRuleContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public MrowContext mrow() {
			return getRuleContext(MrowContext.class,0);
		}
		public MsupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_msup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMsup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMsup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMsup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MsupContext msup() throws RecognitionException {
		MsupContext _localctx = new MsupContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_msup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			actions();
			setState(47);
			match(T__1);
			setState(48);
			match(T__2);
			setState(49);
			mrow();
			setState(50);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MsubContext extends ParserRuleContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public MrowContext mrow() {
			return getRuleContext(MrowContext.class,0);
		}
		public MsubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_msub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMsub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMsub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMsub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MsubContext msub() throws RecognitionException {
		MsubContext _localctx = new MsubContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_msub);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			actions();
			setState(53);
			match(T__4);
			setState(54);
			match(T__2);
			setState(55);
			mrow();
			setState(56);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionsContext extends ParserRuleContext {
		public MiContext mi() {
			return getRuleContext(MiContext.class,0);
		}
		public MnContext mn() {
			return getRuleContext(MnContext.class,0);
		}
		public MoContext mo() {
			return getRuleContext(MoContext.class,0);
		}
		public MsContext ms() {
			return getRuleContext(MsContext.class,0);
		}
		public MfracContext mfrac() {
			return getRuleContext(MfracContext.class,0);
		}
		public MsqrtContext msqrt() {
			return getRuleContext(MsqrtContext.class,0);
		}
		public MbinomContext mbinom() {
			return getRuleContext(MbinomContext.class,0);
		}
		public ActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterActions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitActions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitActions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionsContext actions() throws RecognitionException {
		ActionsContext _localctx = new ActionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_actions);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				mi();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				mn();
				}
				break;
			case OPERATOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				mo();
				}
				break;
			case SYMBOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(61);
				ms();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				mfrac();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(63);
				msqrt();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 7);
				{
				setState(64);
				mbinom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MfracContext extends ParserRuleContext {
		public List<MrowContext> mrow() {
			return getRuleContexts(MrowContext.class);
		}
		public MrowContext mrow(int i) {
			return getRuleContext(MrowContext.class,i);
		}
		public MfracContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mfrac; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMfrac(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMfrac(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMfrac(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MfracContext mfrac() throws RecognitionException {
		MfracContext _localctx = new MfracContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_mfrac);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T__5);
			setState(68);
			match(T__2);
			setState(69);
			mrow();
			setState(70);
			match(T__3);
			setState(71);
			match(T__2);
			setState(72);
			mrow();
			setState(73);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MsqrtContext extends ParserRuleContext {
		public List<MrowContext> mrow() {
			return getRuleContexts(MrowContext.class);
		}
		public MrowContext mrow(int i) {
			return getRuleContext(MrowContext.class,i);
		}
		public MsqrtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_msqrt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMsqrt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMsqrt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMsqrt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MsqrtContext msqrt() throws RecognitionException {
		MsqrtContext _localctx = new MsqrtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_msqrt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__6);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(76);
				match(T__7);
				setState(77);
				mrow();
				setState(78);
				match(T__8);
				}
			}

			setState(82);
			match(T__2);
			setState(83);
			mrow();
			setState(84);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MbinomContext extends ParserRuleContext {
		public List<MrowContext> mrow() {
			return getRuleContexts(MrowContext.class);
		}
		public MrowContext mrow(int i) {
			return getRuleContext(MrowContext.class,i);
		}
		public MbinomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mbinom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMbinom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMbinom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMbinom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MbinomContext mbinom() throws RecognitionException {
		MbinomContext _localctx = new MbinomContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_mbinom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__9);
			setState(87);
			match(T__2);
			setState(88);
			mrow();
			setState(89);
			match(T__3);
			setState(90);
			match(T__2);
			setState(91);
			mrow();
			setState(92);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MiContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public GreekContext greek() {
			return getRuleContext(GreekContext.class,0);
		}
		public FunctionsContext functions() {
			return getRuleContext(FunctionsContext.class,0);
		}
		public MiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MiContext mi() throws RecognitionException {
		MiContext _localctx = new MiContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_mi);
		try {
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				greek();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				functions();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MnContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(GrammarParser.NUMBER, 0); }
		public MnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MnContext mn() throws RecognitionException {
		MnContext _localctx = new MnContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoContext extends ParserRuleContext {
		public TerminalNode OPERATOR() { return getToken(GrammarParser.OPERATOR, 0); }
		public MoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoContext mo() throws RecognitionException {
		MoContext _localctx = new MoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_mo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(OPERATOR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MsContext extends ParserRuleContext {
		public List<TerminalNode> SYMBOL() { return getTokens(GrammarParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(GrammarParser.SYMBOL, i);
		}
		public MsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MsContext ms() throws RecognitionException {
		MsContext _localctx = new MsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ms);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(104); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(103);
					match(SYMBOL);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(106); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GreekContext extends ParserRuleContext {
		public TerminalNode GREEK() { return getToken(GrammarParser.GREEK, 0); }
		public GreekContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_greek; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGreek(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGreek(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGreek(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GreekContext greek() throws RecognitionException {
		GreekContext _localctx = new GreekContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_greek);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__10);
			setState(109);
			match(GREEK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionsContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(GrammarParser.FUNCTION, 0); }
		public FunctionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFunctions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFunctions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFunctions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionsContext functions() throws RecognitionException {
		FunctionsContext _localctx = new FunctionsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__10);
			setState(112);
			match(FUNCTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GrammarParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0013u\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0004\u0001\'\b\u0001\u000b\u0001\f\u0001(\u0001\u0002\u0001\u0002\u0003"+
		"\u0002-\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005B\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007Q\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0003\tb\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0004\fi\b\f\u000b\f\f\fj\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0000\u0000\u0010"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e\u0000\u0000q\u0000 \u0001\u0000\u0000\u0000\u0002&\u0001"+
		"\u0000\u0000\u0000\u0004,\u0001\u0000\u0000\u0000\u0006.\u0001\u0000\u0000"+
		"\u0000\b4\u0001\u0000\u0000\u0000\nA\u0001\u0000\u0000\u0000\fC\u0001"+
		"\u0000\u0000\u0000\u000eK\u0001\u0000\u0000\u0000\u0010V\u0001\u0000\u0000"+
		"\u0000\u0012a\u0001\u0000\u0000\u0000\u0014c\u0001\u0000\u0000\u0000\u0016"+
		"e\u0001\u0000\u0000\u0000\u0018h\u0001\u0000\u0000\u0000\u001al\u0001"+
		"\u0000\u0000\u0000\u001co\u0001\u0000\u0000\u0000\u001er\u0001\u0000\u0000"+
		"\u0000 !\u0005\u0001\u0000\u0000!\"\u0003\u0002\u0001\u0000\"#\u0005\u0001"+
		"\u0000\u0000#\u0001\u0001\u0000\u0000\u0000$\'\u0003\u0004\u0002\u0000"+
		"%\'\u0003\n\u0005\u0000&$\u0001\u0000\u0000\u0000&%\u0001\u0000\u0000"+
		"\u0000\'(\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000"+
		"\u0000\u0000)\u0003\u0001\u0000\u0000\u0000*-\u0003\b\u0004\u0000+-\u0003"+
		"\u0006\u0003\u0000,*\u0001\u0000\u0000\u0000,+\u0001\u0000\u0000\u0000"+
		"-\u0005\u0001\u0000\u0000\u0000./\u0003\n\u0005\u0000/0\u0005\u0002\u0000"+
		"\u000001\u0005\u0003\u0000\u000012\u0003\u0002\u0001\u000023\u0005\u0004"+
		"\u0000\u00003\u0007\u0001\u0000\u0000\u000045\u0003\n\u0005\u000056\u0005"+
		"\u0005\u0000\u000067\u0005\u0003\u0000\u000078\u0003\u0002\u0001\u0000"+
		"89\u0005\u0004\u0000\u00009\t\u0001\u0000\u0000\u0000:B\u0003\u0012\t"+
		"\u0000;B\u0003\u0014\n\u0000<B\u0003\u0016\u000b\u0000=B\u0003\u0018\f"+
		"\u0000>B\u0003\f\u0006\u0000?B\u0003\u000e\u0007\u0000@B\u0003\u0010\b"+
		"\u0000A:\u0001\u0000\u0000\u0000A;\u0001\u0000\u0000\u0000A<\u0001\u0000"+
		"\u0000\u0000A=\u0001\u0000\u0000\u0000A>\u0001\u0000\u0000\u0000A?\u0001"+
		"\u0000\u0000\u0000A@\u0001\u0000\u0000\u0000B\u000b\u0001\u0000\u0000"+
		"\u0000CD\u0005\u0006\u0000\u0000DE\u0005\u0003\u0000\u0000EF\u0003\u0002"+
		"\u0001\u0000FG\u0005\u0004\u0000\u0000GH\u0005\u0003\u0000\u0000HI\u0003"+
		"\u0002\u0001\u0000IJ\u0005\u0004\u0000\u0000J\r\u0001\u0000\u0000\u0000"+
		"KP\u0005\u0007\u0000\u0000LM\u0005\b\u0000\u0000MN\u0003\u0002\u0001\u0000"+
		"NO\u0005\t\u0000\u0000OQ\u0001\u0000\u0000\u0000PL\u0001\u0000\u0000\u0000"+
		"PQ\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0005\u0003\u0000"+
		"\u0000ST\u0003\u0002\u0001\u0000TU\u0005\u0004\u0000\u0000U\u000f\u0001"+
		"\u0000\u0000\u0000VW\u0005\n\u0000\u0000WX\u0005\u0003\u0000\u0000XY\u0003"+
		"\u0002\u0001\u0000YZ\u0005\u0004\u0000\u0000Z[\u0005\u0003\u0000\u0000"+
		"[\\\u0003\u0002\u0001\u0000\\]\u0005\u0004\u0000\u0000]\u0011\u0001\u0000"+
		"\u0000\u0000^b\u0003\u001e\u000f\u0000_b\u0003\u001a\r\u0000`b\u0003\u001c"+
		"\u000e\u0000a^\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000a`\u0001"+
		"\u0000\u0000\u0000b\u0013\u0001\u0000\u0000\u0000cd\u0005\u000f\u0000"+
		"\u0000d\u0015\u0001\u0000\u0000\u0000ef\u0005\u0012\u0000\u0000f\u0017"+
		"\u0001\u0000\u0000\u0000gi\u0005\u0011\u0000\u0000hg\u0001\u0000\u0000"+
		"\u0000ij\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000"+
		"\u0000\u0000k\u0019\u0001\u0000\u0000\u0000lm\u0005\u000b\u0000\u0000"+
		"mn\u0005\f\u0000\u0000n\u001b\u0001\u0000\u0000\u0000op\u0005\u000b\u0000"+
		"\u0000pq\u0005\r\u0000\u0000q\u001d\u0001\u0000\u0000\u0000rs\u0005\u000e"+
		"\u0000\u0000s\u001f\u0001\u0000\u0000\u0000\u0007&(,APaj";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}