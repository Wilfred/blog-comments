(ns blog-comments.views.layout
  (:use [hiccup.core :only [html]]
        [hiccup.page-helpers :only [doctype include-css]]))

(defn common [title & body]
  (html
   (doctype :html5)
   [:head
    [:meta {:charset "utf-8"}]
    [:title title]]
   [:body
    [:div {:id "header"}
     [:h1 {:class "container"} "Blog comments admin"]]
    [:div {:id "content" :class "container"} body]]))

(defn four-oh-four []
  (common "Page Not Found"
          [:p "The page you requested could not be found"]))