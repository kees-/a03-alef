;; ========== INLINE DATA ======================================================
;; Needs to be externalized!
;  The accepted structure is:
;  Key: single alphanumeral as STRING or CHAR
;  Value: MAP containing :content VECTOR, :children MAP
;    :content is a hiccup vector or other DOM component
;    :children is a MAP with structure isomorphic to root
;  Todo: add a third child :metadata as a MAP of node display options
(def routes
  {:content []
   :children {\c {:content [:h3 "this + that"]
                  :children {"a" {:content []
                                  :children {}}
                             \b {:content [:h4 {:style {:color "limegreen"}}
                                           "Go best friend"]
                                 :children {\∑ {:content
                                                [:img {:src "/img/slds.png"}]
                                                :children {}}}}
                             "1" {:content []
                                  :children {}}}}
              "e" {:content [:p "now..."]
                   :children {"e" {:content []
                                   :children {"e" {:content []
                                                   :children []}}}
                              "f" {:content []
                                   :children []}}}
              "g" {:content [:article "but this is how we made it happen"]
                   :children {}}
              "i" {:content [:img {:src "/img/smlxl.png"}]
                   :children {"å" {:content [:p "yaca"]
                                   :children {}}
                              "i" {:content []
                                   :children {}}}}}})
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
       (get-in routes)))
