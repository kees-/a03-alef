(ns a03-alef.db
  (:require
   [a03-alef.router :as router]))

(def default-db
  {:name "Chip of your mug on the floor"
   ;; State for the text field input
   :hash-input ""
   ;; Holds an unchanging map with the entire display tree preloaded.
   :tree {}
   :content {}})
