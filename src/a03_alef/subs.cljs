(ns a03-alef.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::path-box-input
 (fn [db]
   (get-in db [:path-box-content])))

(re-frame/reg-sub
 ::all-content
 (fn [db]
   (get-in db [:content])))
