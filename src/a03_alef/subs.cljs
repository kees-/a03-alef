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
 :<- [::node]
 (fn [node _]
   (-> node :children keys)))

(re-frame/reg-sub
 ::children-signatures
 :<- [::hash]
 :<- [::children]
 (fn [[hash children] _]
   (map (fn [c] (-> hash vec (conj c) ((partial reduce str))))
        children)))

(re-frame/reg-sub
 ::tree
 (fn [db]
   (get-in db [:tree])))

(re-frame/reg-sub
 ::tree-str
 :<- [::tree]
 (fn [content _]
   (->> content
        pprint
        with-out-str)))

(re-frame/reg-sub
 ::node
 (fn [db]
   (get-in db [:content])))

(re-frame/reg-sub
 ::content-focus
 :<- [::node]
 (fn [node _]
   (-> node :content)))

(re-frame/reg-sub
 ::node-str
 :<- [::node]
 (fn [node _]
   (-> node
       pprint
       with-out-str)))
