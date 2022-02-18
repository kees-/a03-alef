(ns a03-alef.db
  (:require [a03-alef.router :as router]))

(def default-db
  {:name "Chip of your mug on the floor"
   :path-box-content ""
   :children (-> router/routes (get-in (router/current-path)) :children)})
