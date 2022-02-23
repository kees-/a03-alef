(ns a03-alef.db
  (:require
   [a03-alef.router :as router]))

(def default-db
  {:name "Chip of your mug on the floor"
   :path-box-content ""
   :base-content nil
   :content (->> (router/current-path) router/navigate (get-in router/routes))})
