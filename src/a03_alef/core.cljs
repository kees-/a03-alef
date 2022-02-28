(ns a03-alef.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [a03-alef.data :as data]
   [a03-alef.events :as events]
   [a03-alef.views :as views]
   [a03-alef.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  ;; Notice this:
  ;  The data tree is retrieved with an ajax GET.
  ;  This string should substitute well.
  (data/load! "/data/tree.edn")
  (dev-setup)
  (mount-root))
