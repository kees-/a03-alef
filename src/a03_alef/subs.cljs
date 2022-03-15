(ns a03-alef.subs
  (:require
   [re-frame.core :as re-frame]))

(def <sub (comp deref re-frame.core/subscribe))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::hash-entry
 (fn [db]
   (get-in db [:hash-input])))

(re-frame/reg-sub
 ::base-content
 (fn [db]
   (get-in db [:base-content])))

(re-frame/reg-sub
 ::all-content
 (fn [db]
   (get-in db [:content])))
