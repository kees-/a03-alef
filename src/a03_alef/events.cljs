(ns a03-alef.events
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
