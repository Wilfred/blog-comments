(ns blog-comments.views.comments
  (:use [hiccup.core :only [html escape-html]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-area text-field submit-button]])
  (:require [blog-comments.views.layout :as layout]))

(defn comment-form []
  [:div {:id "comment-form" :class "sixteen columns alpha omega"}
   (form-to [:post "/"]
            (label "author" "Name")
            (text-field "author")
            (label "body" "Comment")
            (text-area "body")
            (submit-button "SHOUT!"))])

(defn display-comments [comments]
  [:div {:id "comments sixteen columns alpha omega"}
   (map
    (fn [comment]
      (list [:h2 {:class "comment"} (escape-html (:body comment))]
            [:p (:created_at comment)]))
    comments)])

(defn index [comments]
  (layout/common "SHOUTER"
                 (comment-form)
                 [:div {:class "clear"}]
                 (display-comments comments)))