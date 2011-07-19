(ns blog-comments.controllers.posts
  (:use [compojure.core :only [defroutes GET]])
  (:require [blog-comments.views.layout :as layout]
            [blog-comments.views.posts :as view]))

(defn post-create []
  (view/post-create))

(defroutes routes
  (GET "/posts/create" [] (post-create)))