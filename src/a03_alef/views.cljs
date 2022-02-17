(ns a03-alef.views
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs]
   [a03-alef.events :as events]
   [a03-alef.router :as router]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]]))
