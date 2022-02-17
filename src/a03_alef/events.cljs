(ns a03-alef.events
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.db :as db]
   [a03-alef.router :as router]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
