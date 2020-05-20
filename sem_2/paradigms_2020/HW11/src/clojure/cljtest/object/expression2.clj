(definterface expression_interface
    (Evaluate [args])
    (Diff [var])
    (toString [])
)

(declare ZERO_CONST)

(deftype Const [value]
    expression_interface
    (Evaluate [a b] value)
    (Diff [a b] ZERO_CONST)
    (toString [this] (format "%.1f" (double (.value this)))))

(def ZERO_CONST (Const. 0))
(def ONE_CONST (Const. 1))
(def TWO_CONST (Const. 2))

(deftype Var [name]
  expression_interface
  (Evaluate [_ vars] (vars name))
  (Diff [_ var] (if (= name var) ONE_CONST ZERO_CONST))
  (toString [_] (str name)))

(deftype absrtact_unary_operation [f diff c x]
    expression_interface
    (Evaluate [_ vars] (f (.Evaluate x vars)))
    (toString [_] (str "(" c " " (.toString x) ")"))
    (Diff [_ var] (diff var))
)

(deftype absrtact_operation [f diff c x y]
    expression_interface
    (Evaluate [_ vars] (apply f (map #(.Evaluate % vars) [x y])))
    (toString [_] (str "(" c " " (.toString x) (.toString y)")"))
    (Diff [_ var] (diff var))
)

(defn evaluate [obj vars] (.Evaluate obj vars))
(defn toString [obj] (.toString obj))
(defn diff [expr var] (.Diff expr var))
(defn Constant [value] (Const. value))
(defn Variable [name] (Var. name))

(defn Add [x y] (absrtact_operation. + (fn [var] (Add (diff x var) (diff y var))) '+ x y))
(defn Subtract [x y] (absrtact_operation. - (fn [var] (Subtract (diff x var) (diff y var))) '- x y))
(defn Multiply [x y] (absrtact_operation. * (fn [var] (Add (Multiply (diff x var) y) (Multiply (diff y var) x)) '* x y))
(defn Square [a] (absrtact_unary_operation. #(* % %) (fn [var] (Multiply (Multiply TWO_CONST x) (diff x var) 'square a))
(defn Divide [x y] (absrtact_operation. #(/ (double %1) (double %2)) (fn [var] (Divide (Subtract (Multiply (diff x var) y) (Multiply (diff y var) x)) (Square y y)) '/ x y))
(defn Negate [a] (absrtact_unary_operation. - #(Negate (diff a %)) 'negate a))


(def operations {
                 '+      Add
                 '-      Subtract
                 '*      Multiply
                 '/      Divide
                 'negate Negate
                 'square Square
                 })

(defn parse [expr]
  (cond
    (list? expr) (apply (operations (first expr)) (map parse (rest expr)))
    (number? expr) (Constant expr)
    (symbol? expr) (Variable (str expr))))

(defn parseObject [expression] (parse (read-string expression)))