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
		T__31=32, WS=33, LETTER=34, DIGIT=35, SYMBOL=36;
	public static final int
		RULE_math = 0, RULE_mrow = 1, RULE_mi = 2, RULE_greek = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"math", "mrow", "mi", "greek"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'$'", "'\\Gamma'", "'\\Delta'", "'\\Theta'", "'\\Lambda'", "'\\Pi'", 
			"'\\Sigma'", "'\\Phi'", "'\\Psi'", "'\\Omega'", "'\\alpha'", "'\\beta'", 
			"'\\gamma'", "'\\delta'", "'\\epsilon'", "'\\eta'", "'\\theta'", "'\\iota'", 
			"'\\kappa'", "'\\lambda'", "'\\mugreek'", "'\\nu'", "'\\xi'", "'\\pi'", 
			"'\\rho'", "'\\sigma'", "'\\tau'", "'\\upsilon'", "'\\phi'", "'\\chi'", 
			"'\\psi'", "'\\omega'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "WS", "LETTER", 
			"DIGIT", "SYMBOL"
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
			setState(8);
			match(T__0);
			setState(9);
			((MathContext)_localctx).mrow = mrow();
			setState(10);
			match(T__0);

			        ((MathContext)_localctx).tree_ =  new Tree(Nodes.MATH, "", List.of(((MathContext)_localctx).mrow.tree_));
			    
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
		public List<MiContext> mi() {
			return getRuleContexts(MiContext.class);
		}
		public MiContext mi(int i) {
			return getRuleContext(MiContext.class,i);
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
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 120259084288L) != 0) {
				{
				{
				setState(13);
				((MrowContext)_localctx).mi = mi();
				 children_.add(new Tree(Nodes.MI, (((MrowContext)_localctx).mi!=null?_input.getText(((MrowContext)_localctx).mi.start,((MrowContext)_localctx).mi.stop):null), null)); 
				}
				}
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        ((MrowContext)_localctx).tree_ =  new Tree(Nodes.MROW, "", children_);
			    
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
		public List<TerminalNode> LETTER() { return getTokens(GrammarParser.LETTER); }
		public TerminalNode LETTER(int i) {
			return getToken(GrammarParser.LETTER, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(GrammarParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(GrammarParser.DIGIT, i);
		}
		public List<TerminalNode> SYMBOL() { return getTokens(GrammarParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(GrammarParser.SYMBOL, i);
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
		enterRule(_localctx, 4, RULE_mi);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(24); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(23);
					_la = _input.LA(1);
					if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 120259084288L) != 0) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(26); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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
		enterRule(_localctx, 6, RULE_greek);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 8589934588L) != 0) ) {
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
		"\u0004\u0001$\u001f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001\u0011\b\u0001\n\u0001\f\u0001\u0014\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0004\u0002\u0019\b\u0002\u000b\u0002\f\u0002\u001a\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0000\u0000\u0004\u0000\u0002\u0004\u0006"+
		"\u0000\u0002\u0001\u0000\"$\u0001\u0000\u0002 \u001c\u0000\b\u0001\u0000"+
		"\u0000\u0000\u0002\u0012\u0001\u0000\u0000\u0000\u0004\u0018\u0001\u0000"+
		"\u0000\u0000\u0006\u001c\u0001\u0000\u0000\u0000\b\t\u0005\u0001\u0000"+
		"\u0000\t\n\u0003\u0002\u0001\u0000\n\u000b\u0005\u0001\u0000\u0000\u000b"+
		"\f\u0006\u0000\uffff\uffff\u0000\f\u0001\u0001\u0000\u0000\u0000\r\u000e"+
		"\u0003\u0004\u0002\u0000\u000e\u000f\u0006\u0001\uffff\uffff\u0000\u000f"+
		"\u0011\u0001\u0000\u0000\u0000\u0010\r\u0001\u0000\u0000\u0000\u0011\u0014"+
		"\u0001\u0000\u0000\u0000\u0012\u0010\u0001\u0000\u0000\u0000\u0012\u0013"+
		"\u0001\u0000\u0000\u0000\u0013\u0015\u0001\u0000\u0000\u0000\u0014\u0012"+
		"\u0001\u0000\u0000\u0000\u0015\u0016\u0006\u0001\uffff\uffff\u0000\u0016"+
		"\u0003\u0001\u0000\u0000\u0000\u0017\u0019\u0007\u0000\u0000\u0000\u0018"+
		"\u0017\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a"+
		"\u0018\u0001\u0000\u0000\u0000\u001a\u001b\u0001\u0000\u0000\u0000\u001b"+
		"\u0005\u0001\u0000\u0000\u0000\u001c\u001d\u0007\u0001\u0000\u0000\u001d"+
		"\u0007\u0001\u0000\u0000\u0000\u0002\u0012\u001a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}