(ns a03-alef.router
  (:require [cemerick.url :as url]))

(def routes
  {:content []
   :children {"c" {:content [:p "this is this is that"]
                   :children {"a" {:content []
                                   :children {}}
                              "1" {:content []
                                   :children {}}}}
              "e" {:content [:p "now..."]
                   :children {}}
              "g" {:content [:article "but this is how we made it happen"]
                   :children {}}
              "i" {:content [:img {:src "/img/smlxl.png"}]
                   :children {"å" {:content [:p "yaca"]
                                   :children {}}
                              "i" {:content []
                                   :children {}}}}}})

(defn navigate
  ([path]
   (interleave (repeat :children) path))
  ([path & finals]
   (concat (navigate path) finals)))

(defn set-path
  [path]
  (set! (.. js/window -location -pathname) (str "/" path)))

(defn current-path
  []
  (-> js/window
      .-location
      .-pathname
      rest))

(defn scoop-path
  [path]
  (->> path
       set-path
       rest
       navigate
       (get-in routes)))
