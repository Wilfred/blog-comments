(ns blog-comments.views.shouts
  (:use [hiccup.core :only [html escape-html]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-area text-field submit-button]])
  (:require [blog-comments.views.layout :as layout]))

(defn shout-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
   (form-to [:post "/"]
            (label "author" "Name")
            (text-field "author")
            (label "body" "Comment")
            (text-area "body")
            (submit-button "SHOUT!"))])

(defn display-shouts [shouts]
  [:div {:id "shouts sixteen columns alpha omega"}
   (map
    (fn [shout]
      (list [:h2 {:class "shout"} (escape-html (:body shout))]
            [:p (:created_at shout)]))
    shouts)])

(defn index [shouts]
  (layout/common "SHOUTER"
                 (shout-form)
                 [:div {:class "clear"}]
                 (display-shouts shouts)))