(defn op [f]
  (fn [& arguments]
    (fn [args]
      (apply f (mapv #(% args) arguments)))))
(defn constant [value] (fn [_] value))
(defn variable [name] #(get % name))
(def add (op +))
(def subtract (op -))
(def negate (op -))
(def exp (op #(Math/exp %)))
(def ln (op (fn [x] (Math/log (Math/abs x)))))
(def multiply (op *))
(def divide (op #(/ (double %1) %2)))
(def operations
  {'+ add
   '- subtract
   'negate negate
   '* multiply
   '/ divide
   'exp exp
   'ln ln})

(defn parse [expr]
  (cond
    (number? expr) (constant expr)
    (symbol? expr) (variable (str expr))
    (seq? expr) (apply (get operations (first expr)) (map parse (rest expr)))))

(defn parseFunction [expr]
  (parse (read-string expr)))