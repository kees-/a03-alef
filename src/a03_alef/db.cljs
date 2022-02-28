(ns a03-alef.db
  (:require
   [a03-alef.router :as router]))

(def default-db
  {:name "Chip of your mug on the floor"
   :hash-input ""
   :base-content {}
   :content (->> (router/current-hash) router/navigate (get-in router/routes))})
