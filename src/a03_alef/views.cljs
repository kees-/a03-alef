(ns a03-alef.views
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs]
   [a03-alef.events :as events]
   [a03-alef.router :as router]))

(defn hr
  []
  [:hr {:style {:border "none"
                :height ".1px"
                :background-color "silver"}}])

(defn input-panel
  [value]
  [:div.panel
   [:input {:type :text
            :value @value
            :on-change #(re-frame/dispatch [::events/path-box-input
                                            (.. % -target -value)])}]
   [:input {:type :button
            :value "Submit path"
            :on-click #(re-frame/dispatch [::events/refocus-path @value])}]])

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

(defn content-display
  [content]
  (let [display (-> @content :content)]
    [:div.b
     {:style {:float "left"}}
     (if (or (nil? display) (empty? display))
       [:span.t "There is no content at the current node."]
       display)]))

(defn info-panel
  []
  [:div.b
   [:code {:style {:white-space "pre"}}
    (-> @(re-frame/subscribe [::subs/all-content])
        cljs.pprint/pprint
        with-out-str)]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
    [:div.i
     [:h1 @name]
     [list-children]
     [input-panel value]
     [info-panel]]))
