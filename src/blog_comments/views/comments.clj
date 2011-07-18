(ns blog-comments.views.comments
  (:use [hiccup.core :only [html escape-html]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-area text-field submit-button]])
  (:require [blog-comments.views.layout :as layout]))

(defn comment-form []
  [:div {:id "comment-form"}
   (form-to [:post "/"]
            (label "author" "Name")
            (text-field "author")
            (label "body" "Comment")
            (text-area "body")
            (submit-button "SHOUT!"))])

(defn display-all-comments [approved-comments unapproved-comments]
  (letfn [(display-comment [comment]
            (list [:h2 {:class "comment"} (escape-html (:body comment))]
                  [:p "Created at: " (:created_at comment) " by " (:author comment)]
                  (if (not (:approved comment))
                    [:a {:href (str "/comments/" (:id comment) "/approve")} "Approve this comment"]
                    [:a {:href (str "/comments/" (:id comment) "/unapprove")} "Unapprove this comment"])))]
    (list [:a {:href "/comments/create"} "Create new comment"]
          [:div
           [:h2 "Unapproved comments"]
           (map display-comment unapproved-comments)
           [:h2 "Approved comments"]
           (map display-comment approved-comments)])))

(defn comments-all [approved-comments unapproved-comments]
  (layout/common "View all comments"
                 (display-all-comments approved-comments unapproved-comments)))

(defn comment-create []
  (layout/common "New comment"
                 [:h2 "Create a new comment"]
                 (comment-form)))