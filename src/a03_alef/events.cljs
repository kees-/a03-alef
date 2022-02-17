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
 ::path-box-input
 (fn [db [_ input]]
   (assoc-in db [:path-box-content] input)))

(re-frame/reg-event-db
 ::path
 (fn [db [_ content]]
   (router/set-path content)))

(re-frame/reg-event-db
 ::boop
 (fn [db [_ pathname piece]]
   (assoc-in db [:children] (router/scoop-path pathname piece))))
