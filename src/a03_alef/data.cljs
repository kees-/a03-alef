(ns a03-alef.data
  (:require
   [a03-alef.events :as events :refer [>evt]]
   [re-frame.core :as re-frame]
   [ajax.core :as ajax]
   [ajax.edn :as edn]))

(defn handler
  [response]
  (>evt [::events/tree response]))

(defn error-handler
  [response]
  (cljs.pprint/pprint "The request failed. Response:")
  (cljs.pprint/pprint response))

(defn callback
  []
  (>evt [::events/initialize-focus]))

(defn load!
  [source]
  (ajax/GET source
            {:response-format (edn/edn-response-format)
             :handler handler
             :error-handler error-handler
             :finally callback}))
