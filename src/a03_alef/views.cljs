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
            ;REFACTOR
            :on-click #(re-frame/dispatch [::events/refocus-path value])}]])

(defn list-children
  []
  (let [content (re-frame/subscribe [::subs/all-content])
        children (-> @content :children keys)]
    [:div
     (if (nil? children)
       [:span.b "The current node has no children."]
       [:ul.b
        (for [li children]
          [:li {:key li} (.toUpperCase li)])])]))

(defn info-panel
  []
  [:div.b
   [:code {:style {:white-space "pre"}}
    (-> @(re-frame/subscribe [::subs/all-content])
        cljs.pprint/pprint
        with-out-str)]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        value (re-frame/subscribe [::subs/path-box-input])]
    [:div.i
     [:h1 @name]
     [input-panel @value]
     [list-children]
     [info-panel]]))
