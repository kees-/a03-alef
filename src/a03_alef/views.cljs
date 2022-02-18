(ns a03-alef.views
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs]
   [a03-alef.events :as events]
   [a03-alef.router :as router]))

(defn input-panel
  [value]
  [:div.panel
   [:input {:type :text
            :value value
            :on-change #(re-frame/dispatch [::events/path-box-input
                                            (.. % -target -value)])}]
   [:input {:type :button
            :value "Submit path"
            :on-click #(re-frame/dispatch
                        [::events/boop value :children])}]])

(defn list-children
  []
  [:div>ul.b
   (for [li (-> @(re-frame/subscribe [::subs/children]) keys)]
     [:li {:key li} (.toUpperCase li)])])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        value (re-frame/subscribe [::subs/path-box-input])]
    [:div.i
     [:h1 "Hello from " @name]
     (input-panel @value)
     (list-children)]))
