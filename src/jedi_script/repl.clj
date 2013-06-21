(ns jedi-script.repl
  (:use [jedi-script.parser :only (parse stmt prog)]
        [jedi-script.eval :only (default-env evaluate)]))

(def repl-env (default-env))

(defn run-file
  [file]
  (let [stmts (-> file slurp (parse prog))
        env (default-env)]
    (doseq [stm stmts]
      (evaluate stm env))))

(defn eval-and-print [input]
  (try
    (-> input (parse stmt) (evaluate repl-env) println)
    (catch RuntimeException e
      (println "FAIL:" (.getMessage e)))))

(defn repl []
  (print ">> ")
  (flush)
  (let [input (read-line)]
    (cond (= input "exit")
          (println "May the Force be with you")

         :else
         (do (eval-and-print input)
             (recur)))))
      
