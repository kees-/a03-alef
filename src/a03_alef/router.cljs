(ns a03-alef.router
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs :refer [<sub]]))

;; ========== DEAL WITH DATA TREE AND URL PATH =================================
(defn navigate
  "Accepts a seq of chars and returns a seq of keys to navigate tree data."
  ([path]
   (interleave (repeat :children) path))
  ([path & finals]
   (concat (navigate path) finals)))

;; ========== HASH MGMT ========================================================
(defn set-hash
  "Sets hash-state in the active URI to the given string."
  [path]
  (set! (.. js/window -location -hash) (->> path .toLowerCase (str "#"))))

(defn scoop-hash
  [path]
  (->> path
       set-hash
       rest
       navigate
       (get-in (<sub [::subs/tree]))))
