(definterface expression_interface
  (Evaluate [vars])
  (Diff [var])
  (toString []))

(deftype Const [value]
  expression_interface
  (Evaluate [_ _] value)
  (Diff [_ _] (Const. 0))
  (toString [this] (format "%.1f" (double (.value this)))))

(deftype Var [name]
  expression_interface
  (Evaluate [_ vars] (vars name))
  (Diff [_ var] (if (= name var) (Const. 1) (Const. 0)))
  (toString [_] (str name)))

(deftype absrtact_unary_operation [f diff c a]
  expression_interface
  (Evaluate [_ vars] (f (.Evaluate a vars)))
  (toString [_] (str "(" c " " (.toString a) ")"))
  (Diff [_ var] (diff var)))


(deftype absrtact_operation [f diff c x y]
    expression_interface
    (Evaluate [_ vars] (apply f (map #(.Evaluate % vars) [x y])))
    (toString [_] (str "(" c " " (.toString x) " " (.toString y)")"))
    (Diff [_ var] (diff var))
)

(defn evaluate [obj vars] (.Evaluate obj vars))
(defn toString [obj] (.toString obj))
(defn diff [expr var] (.Diff expr var))

(defn Constant [value] (Const. value))
(defn Variable [name] (Var. name))
(defn Add [x y] (absrtact_operation. + (fn [var] (Add (diff x var) (diff y var))) '+ x y))
(defn Subtract [x y] (absrtact_operation. - (fn [var] (Subtract (diff x var) (diff y var))) '- x y))
(defn Multiply [x y] (absrtact_operation. * (fn [var] (Add (Multiply (diff x var) y) (Multiply (diff y var) x))) '* x y))
(defn Square [x] (absrtact_unary_operation. #(* % %) (fn [var] (Multiply (Multiply (Const. 2) x) (diff x var))) 'square x))
(defn Divide [x y] (absrtact_operation. #(/ (double %1) (double %2)) (fn [var] (Divide (Subtract (Multiply (diff x var) y) (Multiply (diff y var) x)) (Square y))) '/ x y))
(defn Negate [x] (absrtact_unary_operation. - #(Negate (diff x %)) 'negate x))
(defn Exp [x] (absrtact_unary_operation. #(Math/exp %) (fn [var] (Multiply (Exp x) (diff x var))) 'exp x))
(defn Ln [x] (absrtact_unary_operation. #(Math/log (Math/abs %)) (fn [var] (Divide (diff x var) x)) 'ln x))

(def operations {
                 '+      Add
                 '-      Subtract
                 '*      Multiply
                 '/      Divide
                 'negate Negate
                 'square Square
                 'exp Exp
                 'ln Ln
                 })

(defn parse [expr]
  (cond
    (list? expr) (apply (operations (first expr)) (map parse (rest expr)))
    (number? expr) (Constant expr)
    (symbol? expr) (Variable (str expr))))

(defn parseObject [expression] (parse (read-string expression)))