grammar Grammar;

@header {
    import classes.Tree;
    import classes.Nodes;

}

math returns [Tree tree_]
    : '$' mrow '$' {
        $tree_ = new Tree(Nodes.MATH, "", List.of($mrow.tree_));
    };

mrow returns [Tree tree_]
    @init {List<Tree> children_ = new ArrayList<Tree>();}
    : (mi { children_.add(new Tree(Nodes.MI, $mi.text, null)); }) * {
        $tree_ = new Tree(Nodes.MROW, "", children_);
    };

mi: (LETTER | DIGIT | SYMBOL)+ ;


WS           : [ \t\r\n]+ -> skip ;
LETTER       : [a-zA-Z];
DIGIT        : [0-9];
SYMBOL       : [^a-zA-Z0-9];

greek :	'\\Gamma' | '\\Delta'  | '\\Theta'   | '\\Lambda' | '\\Pi'
      | '\\Sigma' | '\\Phi'    | '\\Psi'     | '\\Omega'  | '\\alpha' | '\\beta'
      | '\\gamma' | '\\delta'  | '\\epsilon' | '\\eta'    | '\\theta' | '\\iota'
      | '\\kappa' | '\\lambda' | '\\mugreek' | '\\nu'     | '\\xi'    | '\\pi'  | '\\rho'
      | '\\sigma' | '\\tau'    | '\\upsilon' | '\\phi'    | '\\chi'   | '\\psi' | '\\omega' ;
