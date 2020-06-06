;; v+; v-; v*
(defn op [f] (fn [& args] (apply mapv f args)))
(def v+ (op +))
(def v- (op -))
(def v* (op *))
(def vd (op /))
;; scalar; vect
(defn scalar [vA vB] (apply + (v* vA vB)))
(defn getEl [vA vB a b] (- (* (get vA a) (get vB b)) (* (get vA b) (get vB a))))
(defn vect [vA vB] [(getEl vA vB 1 2) (getEl vA vB 2 0) (getEl vA vB 0 1)])
;; v*s
(defn v*s [vA s] (mapv #(* % s) vA))
;; m+; m-; m*
(def m+ (op v+ ))
(def m- (op v- ))
(def m* (op v* ))
(def md (op vd ))
;; transpose
(defn transpose [vA] (apply mapv vector vA))
;; m*s; m*v; m*m
(defn m*s [mA s] (mapv #(v*s % s) mA))
(defn m*v [mA vC] (mapv #(apply + %) (transpose (mapv v*s (transpose mA) vC))))
(defn m*m [mA mB] (transpose (mapv #(m*v mA %1) (transpose mB))))
;; c+;c-;c*;cd
(def c+ (op m+ ))
(def c- (op m- ))
(def c* (op m* ))
(def cd (op md ))