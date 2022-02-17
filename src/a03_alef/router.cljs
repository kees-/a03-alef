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

(defn set-path
  [path]
  (set! (.. js/window -location -pathname) (str "/" path)))

(defn scoop-path
  [arg piece]
  (->> arg
       set-path
       rest
       (get-in routes)
       piece))

(comment
 (->> "a"
      set-path
      rest
      (get-in routes)
      :content))
