(ns jedi-script.core
  (:use [jedi-script.repl :only (repl run-file)])
  (gen-class :main true))

(defn -main
  ([] (repl))
  ([filename] (run-file filename)))
