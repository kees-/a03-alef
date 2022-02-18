(ns a03-alef.db
  (:require [a03-alef.router :as router]))

(def default-db
  {:name "a chip of your mug fallen on the floor"
   :path-box-content ""
   :children (-> router/routes (get-in (router/current-path)) :children)})
