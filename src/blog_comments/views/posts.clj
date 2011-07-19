(ns blog-comments.views.posts
  (:use [hiccup.core :only [html escape-html]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-area text-field submit-button]])
  (:require [blog-comments.views.layout :as layout]))

(defn post-form []
  [:div
   (form-to [:post "/posts/create"]
            (label "name" "Post name")
            (text-field "name")
            (submit-button "Create post"))])

(defn post-create []
  (layout/common "Create a new post"
                 [:h2 "Create new post"]
                 (post-form)))

(defn post-display [post]
  (list [:p "Post: " (:name post)]))