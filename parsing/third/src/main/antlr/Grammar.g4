grammar Grammar;

math : '$' mrow '$' ;

mrow : (indexes | actions) + ;

indexes : msub | msup;
msup : actions '^' '{' mrow '}';
msub : actions '_' '{' mrow '}';

actions : mi | mn | mo | ms | mfrac | msqrt | mbinom;
mfrac : '\\frac' '{'  mrow '}' '{' mrow '}';
msqrt : '\\sqrt' ('[' mrow ']') ? '{' mrow '}';
mbinom : '\\binom' '{' mrow '}' '{' mrow '}';
mi : identifier | greek | functions ;
mn : NUMBER ;
mo : OPERATOR ;
ms : SYMBOL+ ;

greek : '\\' GREEK ;
GREEK : 'alpha' | 'beta' | 'gamma' | 'delta' | 'epsilon' | 'zeta' | 'eta' | 'theta' | 'iota' | 'kappa' | 'lambda' | 'mu' | 'nu' | 'xi' | 'omicron' | 'pi' | 'rho' | 'sigma' | 'tau' | 'upsilon' | 'phi' | 'chi' | 'psi' | 'omega' | 'Gamma' | 'Delta' | 'Theta' | 'Lambda' | 'Xi' | 'Pi' | 'Sigma' | 'Upsilon' | 'Phi' | 'Psi' | 'Omega' ;

functions : '\\' FUNCTION ;
FUNCTION : 'sin' | 'cos' | 'tan' | 'cot';

identifier : IDENTIFIER ;

IDENTIFIER : LETTER+;

NUMBER : [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;

SYMBOL : [^a-zA-Z0-9];

OPERATOR : [+-/*=<>!&|()] ;
LETTER : [a-zA-Z]+ ;