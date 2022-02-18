(ns a03-alef.router
  (:require [cemerick.url :as url]))

(def routes
  {"a" {:content "My acka acka"
        :children {"x" :this
                   "y" :that
                   "z" :then}}
   "b" {:content "Baca baca.. taca"
        :children {"u" :though
                   "v" :there
                   "w" :these}}})

(defn navigate
  ([path]
   (into [:children] (interleave path (repeat :children))))
  ([path & finals]
   (-> path navigate butlast (concat finals))))

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
  [arg piece]
  (->> arg
       set-path
       rest
       (get-in routes)
       piece))
