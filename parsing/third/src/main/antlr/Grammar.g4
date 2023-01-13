grammar Grammar;

@header {
    import classes.Tree;
    import classes.Nodes;

}
//
//math returns [Tree tree_]
//    : '$' mrow '$' {
//        $tree_ = new Tree(Nodes.MATH, "", List.of($mrow.tree_));
//    };
//
//mrow returns [Tree tree_]
//    @init {List<Tree> children_ = new ArrayList<Tree>();}
//    : (mi { children_.add(new Tree(Nodes.MI, $mi.text, null)); }) * {
//        $tree_ = new Tree(Nodes.MROW, "", children_);
//    };
//
//mi: (LETTER | DIGIT | SYMBOL)+ ;
//
//
//WS           : [ \t\r\n]+ -> skip ;
//LETTER       : [a-zA-Z];
//DIGIT        : [0-9];
//SYMBOL       : [^a-zA-Z0-9];
//

math returns [Tree tree_]
    : '$' mrow '$' {
        $tree_ = new Tree(Nodes.MATH, List.of($mrow.tree_));
    };

mrow returns [Tree tree_]
    @init {List<Tree> children_ = new ArrayList<Tree>();}
    : (   mi { children_.add(new Tree(Nodes.MI, $mi.text)); }
        | mn { children_.add(new Tree(Nodes.MN, $mn.text)); }
        | mo { children_.add(new Tree(Nodes.MO, $mo.text)); }
        | ms { children_.add(new Tree(Nodes.MS, $ms.text)); }
        | msqrt { children_.add($msqrt.tree_); }
        | mfrac { children_.add($mfrac.tree_); }
        | paren { children_.add($paren.tree_); }
      ) * { $tree_ = new Tree(Nodes.MROW, children_); };

msqrt returns [Tree tree_]
    @init {Tree tree = new Tree(Nodes.MSQRT);}
    : '\\sqrt'
        square?
        brace {
            tree.addChild($brace.tree_);
            $tree_ = tree;
        };

brace returns [Tree tree_]: '{' mrow '}' { $tree_ =  new Tree(Nodes.MBRACE, List.of($mrow.tree_)); };

square returns [Tree tree_]: '[' mrow ']' { $tree_ =  new Tree(Nodes.MSQUARE, List.of($mrow.tree_)); };

paren returns [Tree tree_]: '(' mrow ')' { $tree_ =  new Tree(Nodes.MPAREN, List.of($mrow.tree_)); };

mfrac returns [Tree tree_]
    @init {Tree tree = new Tree(Nodes.MFRAC, "frac");}
    :  '\\frac'
    brace {tree.addChild($brace.tree_);}
    brace {
        tree.addChild($brace.tree_);
        $tree_ = tree;
    };
mi : IDENTIFIER | greek;

mn : NUMBER ;

mo : OPERATOR ;

ms : SYMBOL+ ;

greek :	'\\Gamma' | '\\Delta'  | '\\Theta'   | '\\Lambda' | '\\Pi'
      | '\\Sigma' | '\\Phi'    | '\\Psi'     | '\\Omega'  | '\\alpha' | '\\beta'
      | '\\gamma' | '\\delta'  | '\\epsilon' | '\\eta'    | '\\theta' | '\\iota'
      | '\\kappa' | '\\lambda' | '\\mugreek' | '\\nu'     | '\\xi'    | '\\pi'  | '\\rho'
      | '\\sigma' | '\\tau'    | '\\upsilon' | '\\phi'    | '\\chi'   | '\\psi' | '\\omega' ;


IDENTIFIER : [a-zA-Z]+ ;

NUMBER : [0-9]+ ;

WS           : [ \t\r\n]+ -> skip ;

SYMBOL       : [^a-zA-Z0-9];

OPERATOR : [+-/*=<>] ;