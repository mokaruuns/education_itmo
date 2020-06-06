(definterface IExpression
  (evaluate [vars])
  (toStr [])
  (diff [var]))

(declare ZERO)
(declare ONE)
(declare TWO)

(deftype Const [value]
  IExpression
  (evaluate [_ _] value)
  (toStr [_] (format "%.1f" (double value)))
  (diff [_ _] ZERO))
(defn Constant [value] (Const. value))

(deftype Var [varName]
  IExpression
  (evaluate [_ vars] (vars varName))
  (toStr [_] (str varName))
  (diff [_ var] (if (= var varName) ONE ZERO)))
(defn Variable [varName] (Var. varName))

(def ZERO (Constant 0))
(def ONE (Constant 1))
(def TWO (Constant 2))
(defn evaluate [obj vars] (.evaluate obj vars))
(defn toString [obj] (.toStr obj))
(defn diff [expr var] (.diff expr var))

(deftype abstractOperation [f op args diff_f]
  IExpression
  (evaluate [_ vars] (apply f (map #(evaluate % vars) args)))
  (toStr [_] (str "(" op " " (clojure.string/join " " (map toString args)) ")"))
  (diff [_ var] (apply diff_f (concat args (map #(diff % var) args)))))

(declare op)

(defn Add [x y] (abstractOperation. + '+ [x y] (op "+")))
(defn Subtract [x y] (abstractOperation. - '- [x y] (op "-")))
(defn Multiply [x y] (abstractOperation. * '* [x y] (op "*")))
(defn Divide [x y] (abstractOperation. #(/ %1 (double %2)) '/ [x y] (op "/")))
(defn Negate [x] (abstractOperation. - 'negate [x] (op "negate")))
(defn Sqrt [x] (abstractOperation. #(Math/sqrt (Math/abs (double %))) 'sqrt [x] (op "sqrt")))
(defn Exp [x] (abstractOperation. #(Math/exp %) 'exp [x] (op "exp")))
(defn Ln [x] (abstractOperation. #(Math/log (Math/abs (double %))) 'ln [x] (op "ln")))

(defn op [c]
  (case c
    "+" (fn [_ _ dx dy] (Add dx dy))
    "-" (fn [_ _ dx dy] (Subtract dx dy))
    "*" (fn [x y dx dy] (Add (Multiply dx y) (Multiply x dy)))
    "/" (fn [x y dx dy] (Divide (Subtract (Multiply dx y) (Multiply x dy)) (Multiply y y)))
    "negate" (fn [_ dx] (Negate dx))
    "sqrt" (fn [x dx] (Divide (Multiply x dx) (Multiply TWO (Sqrt (Multiply (Multiply x x) x)))))
    "exp" (fn [x dx] (Multiply (Exp x) dx))
    "ln" (fn [x dx] (Divide dx x))
    )
  )

(def operations {
                 '+      Add
                 '-      Subtract
                 '*      Multiply
                 '/      Divide
                 'negate Negate
                 'ln     Ln
                 'exp    Exp
                 })

(defn parse [expr]
  (cond
    (list? expr) (apply (operations (first expr)) (map parse (rest expr)))
    (number? expr) (Constant expr)
    (symbol? expr) (Variable (str expr))))

(defn parseObject [expression] (parse (read-string expression)))