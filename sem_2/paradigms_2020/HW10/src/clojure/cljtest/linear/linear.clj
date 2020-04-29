;; v+; v-; v*
(defn v+ [vA vB] (mapv + vA vB))
(defn v- [vA vB] (mapv - vA vB))
(defn v* [vA vB] (mapv * vA vB))
(defn vd [vA vB] (mapv / vA vB))
;; scalar; vect
(defn scalar [vA vB] (apply + (v* vA vB)))
(defn getEl [vA vB a b] (- (* (get vA a) (get vB b)) (* (get vA b) (get vB a))))
(defn vect [vA vB] [(getEl vA vB 1 2) (getEl vA vB 2 0) (getEl vA vB 0 1)])
;; v*s
(defn multSS [x] #(* %1 x))
(defn v*s [vA s] (mapv (multSS s) vA))
;; m+; m-; m*
(defn m+ [mA mB] (mapv #(mapv + %1 %2) mA mB))
(defn m- [mA mB] (mapv #(mapv - %1 %2) mA mB))
(defn m* [mA mB] (mapv #(mapv * %1 %2) mA mB))
(defn md [mA mB] (mapv #(mapv / %1 %2) mA mB))
;; transpose
(defn transpose [vA] (apply mapv vector vA))
;; m*s; m*v; m*m
(defn m*s [mA s] (mapv #(mapv (multSS s) %1) mA))
(defn m*v [mA vC] (mapv #(apply + %1) (transpose (mapv v*s (transpose mA) vC))))
(defn m*m [mA mB] (vec (for [x mA] (vec (for [y (transpose mB)] (scalar x y))))))
;; s+;s-;s*
(defn commonOp [op sA sB] (if (number? sA) (op sA sB) (mapv #(commonOp op %1 %2) sA sB)))
;; c+;c-;c*;cd
(defn c+ [sA sB] (commonOp + sA sB))
(defn c- [sA sB] (commonOp - sA sB))
(defn c* [sA sB] (commonOp * sA sB))
(defn cd [sA sB] (commonOp / sA sB))