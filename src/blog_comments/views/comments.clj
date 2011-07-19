(ns blog-comments.views.comments
  (:use [hiccup.core :only [html escape-html]]
        [hiccup.form-helpers :only [form-to label text-area text-field submit-button]])
  (:require [blog-comments.views.layout :as layout]))

(defn comment-form []
  [:div {:id "comment-form"}
   (form-to [:post "/comments/create"]
            (label "author" "Name")
            (text-field "author")
            (label "body" "Comment")
            (text-area "body")
            (submit-button "SHOUT!"))])

(defn display-comment [comment]
  (list [:h2 {:class "comment"} (escape-html (:body comment))]
        [:p "Created at: " (:created_at comment) " by " (:author comment)]
        (if (not (:approved comment))
          [:a {:href (str "/comments/" (:id comment) "/approve")} "Approve this comment"]
          [:a {:href (str "/comments/" (:id comment) "/unapprove")} "Unapprove this comment"])))

(defn comments-all [approved-comments unapproved-comments]
  (layout/common "View all comments"
                 [:p [:a {:href "/comments/create"} "Create new comment"]]
                 [:p [:a {:href "/posts/create"} "Create new post"]]
                 [:div
                  [:h2 "Unapproved comments"]
                  (map display-comment unapproved-comments)
                  [:h2 "Approved comments"]
                  (map display-comment approved-comments)]))

(defn comment-create []
  (layout/common "New comment"
                 [:h2 "Create a new comment"]
                 (comment-form)))