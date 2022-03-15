(ns a03-alef.subs
  (:require
   [cljs.pprint :refer [pprint]]
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
 ::hash
 (fn [_]
   (-> js/window
       .-location
       .-hash
       rest)))

(re-frame/reg-sub
 ::children
 :<- [::all-content]
 (fn [content _]
   (-> content :children keys)))

(re-frame/reg-sub
 ::children-signatures
 :<- [::hash]
 :<- [::children]
 (fn [[hash children] _]
   (map (fn [c] (reduce str (-> hash vec (conj c))))
        children)))

(re-frame/reg-sub
 ::base-content
 (fn [db]
   (get-in db [:base-content])))

(re-frame/reg-sub
 ::base-content-str
 (fn [_ _]
   (re-frame/subscribe [::base-content]))
 (fn [content _]
   (->> content
        pprint
        with-out-str)))

(re-frame/reg-sub
 ::all-content
 (fn [db]
   (get-in db [:content])))

(re-frame/reg-sub
 ::content-focus
 (fn [_ _]
   (re-frame/subscribe [::all-content]))
 (fn [all-content _]
   (-> all-content :content)))

(re-frame/reg-sub
 ::all-content-str
 (fn [_ _]
   (re-frame/subscribe [::all-content]))
 (fn [content _]
   (-> content
       pprint
       with-out-str)))
