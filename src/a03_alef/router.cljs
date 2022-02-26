(ns a03-alef.router)

;; ========== INLINE DATA ======================================================
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

;; ========== DEAL WITH DATA TREE AND URL PATH =================================
(defn navigate
  ([path]
   (interleave (repeat :children) path))
  ([path & finals]
   (concat (navigate path) finals)))

;; ========== HASH MGMT ========================================================
(defn set-hash
  [path]
  (set! (.. js/window -location -hash) (str "#" path)))

(defn current-hash
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
