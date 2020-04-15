;; v+; v-; v*
(defn v+ [vA vB] (mapv + vA vB))
(defn v- [vA vB] (mapv - vA vB))
(defn v* [vA vB] (mapv * vA vB))
;; scalar; vect
(defn scalar [vA vB] (apply + (v* vA vB)))
(defn vect [vA vB] [(- (* (get vA 1) (get vB 2)) (* (get vA 2) (get vB 1)))
                    (- (* (get vA 2) (get vB 0)) (* (get vA 0) (get vB 2)))
                    (- (* (get vA 0) (get vB 1)) (* (get vA 1) (get vB 0)))])
;; v*s
(defn *s [x] #(* %1 x))
(defn v*s [vA s] (mapv (*s s) vA))
;; m+; m-; m*
(defn m+ [mA mB] (mapv #(mapv + %1 %2) mA mB))
(defn m- [mA mB] (mapv #(mapv - %1 %2) mA mB))
(defn m* [mA mB] (mapv #(mapv * %1 %2) mA mB))
;; transpose
(defn transpose [vA] (apply mapv vector vA))
;; m*s; m*v; m*m
(defn m*s [mA s] (mapv #(mapv (*s s) %1) mA))
(defn m*v [mA vC] (mapv #(apply + %) (transpose (mapv v*s (transpose mA) vC))))
(defn m*m [mA mB] (vec (for [x mA] (vec (for [y (transpose mB)] (scalar x y))))))
;; s+;s-;s*
(defn commonOp [op sA sB] (if (number? sA) (op sA sB) (mapv #(commonOp op %1 %2) sA sB)))
(defn s+ [sA sB] (commonOp + sA sB))
(defn s- [sA sB] (commonOp - sA sB))
(defn s* [sA sB] (commonOp * sA sB))