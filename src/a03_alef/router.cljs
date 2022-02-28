(ns a03-alef.router
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs]))

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
  (set! (.. js/window -location -hash) (str "#" path)))

(defn current-hash
  "(rest) on a hash string will strip the hash and separate all chars to a seq."
  []
  (-> js/window
      .-location
      .-hash
      rest))

(defn scoop-hash
  [path]
  (->> path
       set-hash
       rest
       navigate
       (get-in @(re-frame/subscribe [::subs/base-content]))))
