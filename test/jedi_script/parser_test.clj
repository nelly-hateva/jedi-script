(ns jedi-script.parser-test
  (:use clojure.test
        jedi-script.parser))

(defn- normalize [ast]
  (if (= (count ast) 1)
    (first ast)
    ast))

(deftest arithmetic-operators-test
  (are [input expected] (= (-> input parse normalize) expected)
       "a + b" '(+ a b)
       "a - b" '(- a b)
       "a * b" '(* a b)
       "a / b" '(/ a b)
       "a % b" '(% a b)
;       "a++" '(++ a)
;       "a--" '(-- a)
))

(deftest comparison-operators-test
  (are [input expected] (= (-> input parse normalize) expected)
       "a == b" '(== a b)
       "a != b" '(!= a b)
       "a > b" '(> a b)
       "a >= b" '(>= a b)
       "a < b" '(< a b)
       "a <= b" '(<= a b)))

(deftest logical-operators-test
  (are [input expected] (= (-> input parse normalize) expected)
       "a && b" '(and a b)
       "a || b" '(or a b)
       "!a" '(! a)))

(deftest bitwise-operators-test
  (are [input expected] (= (-> input parse normalize) expected)
;       "a & b" '(& a b)
;       "a | b" '(| a b)
;       "a ^ b" '(^ a b)
;       "~a" '(~ a)
       "a >> b" '(>> a b)
       "a << b" '(<< a b)
;       "a >>> b" '(>>> a b)
))

(deftest assignment-operators-test
  (are [input expected] (= (-> input parse normalize) expected)
       "a = b" '(set! a b)
;       "a += b" '(set! a (+ a b))
;       "a -= b" '(set! a (- a b))
;       "a *= b" '(set! a (* a b))
;       "a /= b" '(set! a (/ a b))
;       "a %= b" '(set! a (% a b))
))

(deftest miscellaneous-operator-test
  (are [input expected] (= (-> input parse normalize) expected)
;       "a ? b : c" '()
       "typeof a" '(typeof a)))

(deftest nested-expressions-test
  (are [input expected] (= (-> input parse normalize) expected)
       "x + y * z + 5" '(+ (+ x (* y z)) 5)
))
(run-tests)
