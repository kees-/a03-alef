(ns a03-alef.events
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.db :as db]
   [a03-alef.router :as router]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::base-content
 (fn [db [_ input]]
   (assoc-in db [:base-content] input)))

(re-frame/reg-event-db
 ::hash-entry
 (fn [db [_ input]]
   (assoc-in db [:hash-input] input)))

;; ========== HASH MGMT ========================================================
(re-frame/reg-event-db
 ::refocus-hash
 (fn [db [_ pathname]]
   (assoc-in db [:content] (router/scoop-hash pathname))))
