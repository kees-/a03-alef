(ns a03-alef.db
  (:require
   [a03-alef.router :as router]))

(def default-db
  {:name "Chip of your mug on the floor"
   ;; State for the text field input
   :hash-input ""
   ;; Not yet used
   ;  Will store an unchanging copy of the entire data tree on load
   :base-content {}
   ;; The data tree with the currently viewed node as root
   :content (->> (router/current-hash) router/navigate (get-in router/routes))})

;; Currently, the logic is to load the root on page load.
;  The data is defined inline.
;  The next major step is to outsource site structures to external files.
;  Then, the entire tree stays in :base-content, and :content is only a focus.
