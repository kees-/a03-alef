(ns a03-alef.views
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs]
   [a03-alef.events :as events]
   [a03-alef.router :as router]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.i
     [:h1
      "Hello from " @name]
     [:div
      [:input {:type :text
               :value @(re-frame/subscribe [::subs/path-box-input])
               :on-change #(re-frame/dispatch [::events/path-box-input
                                               (.. % -target -value)])}]
      [:input {:type :button
               :value "Submit path"
               :on-click #(re-frame/dispatch
                           [::events/boop
                            @(re-frame/subscribe [::subs/path-box-input])
                            :children])}]]
     [:div
      [:ul.b
       (for [li (-> @(re-frame/subscribe [::subs/children])
                    keys)]
         [:li {:key li} (.toUpperCase li)])]]]))
