(ns jedi-script.parser
  (:use (blancas.kern (core :exclude (parse) :as kern))
        blancas.kern.lexer.java-style))

(declare expr)
(declare stmt)
(declare condition)

(def assign
  (bind [id identifier
         _ (token "=")
         ex expr]
    (return (list 'set! (symbol id) ex))))

(def stmt (<|> (<:> assign) condition expr))
(def prog (bind [stmts (many1 stmt)]
                (return (apply list stmts))))

(defn parse
  ([input] (parse input prog))
  ([input parser]
   (let [result (kern/parse parser input)]
     (when-not (:ok result)
       (print-error result)
       (throw (RuntimeException. "Parsing failed")))
     (:value result))))
