(ns a03-alef.views
  (:require
   [re-frame.core :as re-frame]
   [a03-alef.subs :as subs]
   [a03-alef.events :as events]
   [a03-alef.router :as router]))

;; ========== INDIVIDUAL COMPONENTS ============================================
(defn hr
  []
  [:hr {:style {:border "none"
                :height ".1px"
                :margin "10px 15px"
                :background-color "silver"}}])

(defn input-panel
  [value]
  [:div.panel
   [:input {:type :button
            :value "Back"
            :on-click #(->> (router/current-path)
                            butlast
                            (reduce str)
                            router/set-path)}]
   [:input {:type :text
            :value @value
            :on-change #(re-frame/dispatch [::events/path-box-input
                                            (.. % -target -value)])}]
   [:input {:type :button
            :value "Submit path"
            :on-click #(re-frame/dispatch [::events/refocus-path @value])}]])

(defn list-children
  [content]
  (let [children (-> @content :children keys)]
    [:div
     {:style {:float "left"}}
     (if (nil? children)
       [:span.b.t "The current node has no children."]
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

;; ========== COMPONENT ASSEMBLY ===============================================
(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        value (re-frame/subscribe [::subs/path-box-input])
        content (re-frame/subscribe [::subs/all-content])]
    [:div#wrapper.i
     [:h1 @name]
     [input-panel value]
     [hr]
     [:div
      {:style {:overflow "hidden"}}
      [list-children content]
      [content-display content]]
     [hr]
     [info-panel]]))
