// Generated from java-escape by ANTLR 4.11.1

    import classes.Tree;
    import classes.Nodes;


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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, IDENTIFIER=41, NUMBER=42, WS=43, SYMBOL=44, OPERATOR=45;
	public static final int
		RULE_math = 0, RULE_mrow = 1, RULE_msqrt = 2, RULE_brace = 3, RULE_square = 4, 
		RULE_paren = 5, RULE_mfrac = 6, RULE_mi = 7, RULE_mn = 8, RULE_mo = 9, 
		RULE_ms = 10, RULE_greek = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"math", "mrow", "msqrt", "brace", "square", "paren", "mfrac", "mi", "mn", 
			"mo", "ms", "greek"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'$'", "'\\sqrt'", "'{'", "'}'", "'['", "']'", "'('", "')'", "'\\frac'", 
			"'\\Gamma'", "'\\Delta'", "'\\Theta'", "'\\Lambda'", "'\\Pi'", "'\\Sigma'", 
			"'\\Phi'", "'\\Psi'", "'\\Omega'", "'\\alpha'", "'\\beta'", "'\\gamma'", 
			"'\\delta'", "'\\epsilon'", "'\\eta'", "'\\theta'", "'\\iota'", "'\\kappa'", 
			"'\\lambda'", "'\\mugreek'", "'\\nu'", "'\\xi'", "'\\pi'", "'\\rho'", 
			"'\\sigma'", "'\\tau'", "'\\upsilon'", "'\\phi'", "'\\chi'", "'\\psi'", 
			"'\\omega'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "IDENTIFIER", "NUMBER", "WS", "SYMBOL", 
			"OPERATOR"
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
		public Tree tree_;
		public MrowContext mrow;
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
			setState(24);
			match(T__0);
			setState(25);
			((MathContext)_localctx).mrow = mrow();
			setState(26);
			match(T__0);

			        ((MathContext)_localctx).tree_ =  new Tree(Nodes.MATH, List.of(((MathContext)_localctx).mrow.tree_));
			    
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
		public Tree tree_;
		public MiContext mi;
		public MnContext mn;
		public MoContext mo;
		public MsContext ms;
		public MsqrtContext msqrt;
		public MfracContext mfrac;
		public ParenContext paren;
		public List<MiContext> mi() {
			return getRuleContexts(MiContext.class);
		}
		public MiContext mi(int i) {
			return getRuleContext(MiContext.class,i);
		}
		public List<MnContext> mn() {
			return getRuleContexts(MnContext.class);
		}
		public MnContext mn(int i) {
			return getRuleContext(MnContext.class,i);
		}
		public List<MoContext> mo() {
			return getRuleContexts(MoContext.class);
		}
		public MoContext mo(int i) {
			return getRuleContext(MoContext.class,i);
		}
		public List<MsContext> ms() {
			return getRuleContexts(MsContext.class);
		}
		public MsContext ms(int i) {
			return getRuleContext(MsContext.class,i);
		}
		public List<MsqrtContext> msqrt() {
			return getRuleContexts(MsqrtContext.class);
		}
		public MsqrtContext msqrt(int i) {
			return getRuleContext(MsqrtContext.class,i);
		}
		public List<MfracContext> mfrac() {
			return getRuleContexts(MfracContext.class);
		}
		public MfracContext mfrac(int i) {
			return getRuleContext(MfracContext.class,i);
		}
		public List<ParenContext> paren() {
			return getRuleContexts(ParenContext.class);
		}
		public ParenContext paren(int i) {
			return getRuleContext(ParenContext.class,i);
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
		List<Tree> children_ = new ArrayList<Tree>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 61572651155076L) != 0) {
				{
				setState(50);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
				case T__10:
				case T__11:
				case T__12:
				case T__13:
				case T__14:
				case T__15:
				case T__16:
				case T__17:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case T__35:
				case T__36:
				case T__37:
				case T__38:
				case T__39:
				case IDENTIFIER:
					{
					setState(29);
					((MrowContext)_localctx).mi = mi();
					 children_.add(new Tree(Nodes.MI, (((MrowContext)_localctx).mi!=null?_input.getText(((MrowContext)_localctx).mi.start,((MrowContext)_localctx).mi.stop):null))); 
					}
					break;
				case NUMBER:
					{
					setState(32);
					((MrowContext)_localctx).mn = mn();
					 children_.add(new Tree(Nodes.MN, (((MrowContext)_localctx).mn!=null?_input.getText(((MrowContext)_localctx).mn.start,((MrowContext)_localctx).mn.stop):null))); 
					}
					break;
				case OPERATOR:
					{
					setState(35);
					((MrowContext)_localctx).mo = mo();
					 children_.add(new Tree(Nodes.MO, (((MrowContext)_localctx).mo!=null?_input.getText(((MrowContext)_localctx).mo.start,((MrowContext)_localctx).mo.stop):null))); 
					}
					break;
				case SYMBOL:
					{
					setState(38);
					((MrowContext)_localctx).ms = ms();
					 children_.add(new Tree(Nodes.MS, (((MrowContext)_localctx).ms!=null?_input.getText(((MrowContext)_localctx).ms.start,((MrowContext)_localctx).ms.stop):null))); 
					}
					break;
				case T__1:
					{
					setState(41);
					((MrowContext)_localctx).msqrt = msqrt();
					 children_.add(((MrowContext)_localctx).msqrt.tree_); 
					}
					break;
				case T__8:
					{
					setState(44);
					((MrowContext)_localctx).mfrac = mfrac();
					 children_.add(((MrowContext)_localctx).mfrac.tree_); 
					}
					break;
				case T__6:
					{
					setState(47);
					((MrowContext)_localctx).paren = paren();
					 children_.add(((MrowContext)_localctx).paren.tree_); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 ((MrowContext)_localctx).tree_ =  new Tree(Nodes.MROW, children_); 
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
		public Tree tree_;
		public BraceContext brace;
		public BraceContext brace() {
			return getRuleContext(BraceContext.class,0);
		}
		public SquareContext square() {
			return getRuleContext(SquareContext.class,0);
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
		enterRule(_localctx, 4, RULE_msqrt);
		Tree tree = new Tree(Nodes.MSQRT);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__1);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(58);
				square();
				}
			}

			setState(61);
			((MsqrtContext)_localctx).brace = brace();

			            tree.addChild(((MsqrtContext)_localctx).brace.tree_);
			            ((MsqrtContext)_localctx).tree_ =  tree;
			        
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
	public static class BraceContext extends ParserRuleContext {
		public Tree tree_;
		public MrowContext mrow;
		public MrowContext mrow() {
			return getRuleContext(MrowContext.class,0);
		}
		public BraceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_brace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBrace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBrace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitBrace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BraceContext brace() throws RecognitionException {
		BraceContext _localctx = new BraceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_brace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__2);
			setState(65);
			((BraceContext)_localctx).mrow = mrow();
			setState(66);
			match(T__3);
			 ((BraceContext)_localctx).tree_ =   new Tree(Nodes.MBRACE, List.of(((BraceContext)_localctx).mrow.tree_)); 
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
	public static class SquareContext extends ParserRuleContext {
		public Tree tree_;
		public MrowContext mrow;
		public MrowContext mrow() {
			return getRuleContext(MrowContext.class,0);
		}
		public SquareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_square; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSquare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSquare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSquare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SquareContext square() throws RecognitionException {
		SquareContext _localctx = new SquareContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_square);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__4);
			setState(70);
			((SquareContext)_localctx).mrow = mrow();
			setState(71);
			match(T__5);
			 ((SquareContext)_localctx).tree_ =   new Tree(Nodes.MSQUARE, List.of(((SquareContext)_localctx).mrow.tree_)); 
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
	public static class ParenContext extends ParserRuleContext {
		public Tree tree_;
		public MrowContext mrow;
		public MrowContext mrow() {
			return getRuleContext(MrowContext.class,0);
		}
		public ParenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenContext paren() throws RecognitionException {
		ParenContext _localctx = new ParenContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_paren);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__6);
			setState(75);
			((ParenContext)_localctx).mrow = mrow();
			setState(76);
			match(T__7);
			 ((ParenContext)_localctx).tree_ =   new Tree(Nodes.MPAREN, List.of(((ParenContext)_localctx).mrow.tree_)); 
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
		public Tree tree_;
		public BraceContext brace;
		public List<BraceContext> brace() {
			return getRuleContexts(BraceContext.class);
		}
		public BraceContext brace(int i) {
			return getRuleContext(BraceContext.class,i);
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
		Tree tree = new Tree(Nodes.MFRAC, "frac");
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__8);
			setState(80);
			((MfracContext)_localctx).brace = brace();
			tree.addChild(((MfracContext)_localctx).brace.tree_);
			setState(82);
			((MfracContext)_localctx).brace = brace();

			        tree.addChild(((MfracContext)_localctx).brace.tree_);
			        ((MfracContext)_localctx).tree_ =  tree;
			    
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
		public TerminalNode IDENTIFIER() { return getToken(GrammarParser.IDENTIFIER, 0); }
		public GreekContext greek() {
			return getRuleContext(GreekContext.class,0);
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
		enterRule(_localctx, 14, RULE_mi);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(IDENTIFIER);
				}
				break;
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				greek();
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
		enterRule(_localctx, 16, RULE_mn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
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
		enterRule(_localctx, 18, RULE_mo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
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
		enterRule(_localctx, 20, RULE_ms);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(93);
					match(SYMBOL);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(96); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
		enterRule(_localctx, 22, RULE_greek);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 2199023254528L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		"\u0004\u0001-e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007"+
		"\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u00013\b\u0001\n\u0001\f\u00016\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002<\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0003\u0007X\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0004\n_\b\n\u000b\n\f\n`\u0001\u000b\u0001\u000b\u0001\u000b\u0000"+
		"\u0000\f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000"+
		"\u0001\u0001\u0000\n(b\u0000\u0018\u0001\u0000\u0000\u0000\u00024\u0001"+
		"\u0000\u0000\u0000\u00049\u0001\u0000\u0000\u0000\u0006@\u0001\u0000\u0000"+
		"\u0000\bE\u0001\u0000\u0000\u0000\nJ\u0001\u0000\u0000\u0000\fO\u0001"+
		"\u0000\u0000\u0000\u000eW\u0001\u0000\u0000\u0000\u0010Y\u0001\u0000\u0000"+
		"\u0000\u0012[\u0001\u0000\u0000\u0000\u0014^\u0001\u0000\u0000\u0000\u0016"+
		"b\u0001\u0000\u0000\u0000\u0018\u0019\u0005\u0001\u0000\u0000\u0019\u001a"+
		"\u0003\u0002\u0001\u0000\u001a\u001b\u0005\u0001\u0000\u0000\u001b\u001c"+
		"\u0006\u0000\uffff\uffff\u0000\u001c\u0001\u0001\u0000\u0000\u0000\u001d"+
		"\u001e\u0003\u000e\u0007\u0000\u001e\u001f\u0006\u0001\uffff\uffff\u0000"+
		"\u001f3\u0001\u0000\u0000\u0000 !\u0003\u0010\b\u0000!\"\u0006\u0001\uffff"+
		"\uffff\u0000\"3\u0001\u0000\u0000\u0000#$\u0003\u0012\t\u0000$%\u0006"+
		"\u0001\uffff\uffff\u0000%3\u0001\u0000\u0000\u0000&\'\u0003\u0014\n\u0000"+
		"\'(\u0006\u0001\uffff\uffff\u0000(3\u0001\u0000\u0000\u0000)*\u0003\u0004"+
		"\u0002\u0000*+\u0006\u0001\uffff\uffff\u0000+3\u0001\u0000\u0000\u0000"+
		",-\u0003\f\u0006\u0000-.\u0006\u0001\uffff\uffff\u0000.3\u0001\u0000\u0000"+
		"\u0000/0\u0003\n\u0005\u000001\u0006\u0001\uffff\uffff\u000013\u0001\u0000"+
		"\u0000\u00002\u001d\u0001\u0000\u0000\u00002 \u0001\u0000\u0000\u0000"+
		"2#\u0001\u0000\u0000\u00002&\u0001\u0000\u0000\u00002)\u0001\u0000\u0000"+
		"\u00002,\u0001\u0000\u0000\u00002/\u0001\u0000\u0000\u000036\u0001\u0000"+
		"\u0000\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000057\u0001"+
		"\u0000\u0000\u000064\u0001\u0000\u0000\u000078\u0006\u0001\uffff\uffff"+
		"\u00008\u0003\u0001\u0000\u0000\u00009;\u0005\u0002\u0000\u0000:<\u0003"+
		"\b\u0004\u0000;:\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<=\u0001"+
		"\u0000\u0000\u0000=>\u0003\u0006\u0003\u0000>?\u0006\u0002\uffff\uffff"+
		"\u0000?\u0005\u0001\u0000\u0000\u0000@A\u0005\u0003\u0000\u0000AB\u0003"+
		"\u0002\u0001\u0000BC\u0005\u0004\u0000\u0000CD\u0006\u0003\uffff\uffff"+
		"\u0000D\u0007\u0001\u0000\u0000\u0000EF\u0005\u0005\u0000\u0000FG\u0003"+
		"\u0002\u0001\u0000GH\u0005\u0006\u0000\u0000HI\u0006\u0004\uffff\uffff"+
		"\u0000I\t\u0001\u0000\u0000\u0000JK\u0005\u0007\u0000\u0000KL\u0003\u0002"+
		"\u0001\u0000LM\u0005\b\u0000\u0000MN\u0006\u0005\uffff\uffff\u0000N\u000b"+
		"\u0001\u0000\u0000\u0000OP\u0005\t\u0000\u0000PQ\u0003\u0006\u0003\u0000"+
		"QR\u0006\u0006\uffff\uffff\u0000RS\u0003\u0006\u0003\u0000ST\u0006\u0006"+
		"\uffff\uffff\u0000T\r\u0001\u0000\u0000\u0000UX\u0005)\u0000\u0000VX\u0003"+
		"\u0016\u000b\u0000WU\u0001\u0000\u0000\u0000WV\u0001\u0000\u0000\u0000"+
		"X\u000f\u0001\u0000\u0000\u0000YZ\u0005*\u0000\u0000Z\u0011\u0001\u0000"+
		"\u0000\u0000[\\\u0005-\u0000\u0000\\\u0013\u0001\u0000\u0000\u0000]_\u0005"+
		",\u0000\u0000^]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`^\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a\u0015\u0001\u0000\u0000"+
		"\u0000bc\u0007\u0000\u0000\u0000c\u0017\u0001\u0000\u0000\u0000\u0005"+
		"24;W`";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}