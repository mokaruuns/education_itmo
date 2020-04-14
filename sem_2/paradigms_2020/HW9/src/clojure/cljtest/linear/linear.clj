;; v+;v-;v*
(defn v+ [vA vB] (mapv + vA vB))
(defn v- [vA vB] (mapv - vA vB))
(defn v* [vA vB] (mapv * vA vB))
;; scalar/vect
(defn scalar [vA vB] (apply + (v* vA vB)))
(defn vect [vA vB] [
                    (-(* (get vA 1) (get vB 2)) (* (get vA 2) (get vB 1)))
                    (-(* (get vA 2) (get vB 0)) (* (get vA 0) (get vB 2)))
                    (-(* (get vA 0) (get vB 1)) (* (get vA 1) (get vB 0)))
                    ])
;; v*s
(defn *s [x] #(* % x))
(defn v*s [vA s] (mapv (*s s) vA))
;; m+;m-;m*
(defn m+ [mA mB] (mapv #(mapv + %1 %2) mA mB))
(defn m- [mA mB] (mapv #(mapv - %1 %2) mA mB))
(defn m* [mA mB] (mapv #(mapv * %1 %2) mA mB))
;; m*s
(defn m*s [mA s] (mapv #(mapv (*s s) %1) mA))
;; transpose
(defn transpose [vA] (apply mapv vector vA))
;; m*v
(defn m*v [mA vC] (mapv #(reduce + %) (transpose (mapv v*s (transpose mA) vC))))
;; m*m
(defn m*m [mA mB] (vec (for [x mA] (vec (for [y (transpose mB)] (scalar x y))))))