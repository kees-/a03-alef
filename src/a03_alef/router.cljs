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
