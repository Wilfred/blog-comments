(ns blog-comments.controllers.posts
  (:use [compojure.core :only [defroutes GET]])
  (:require [blog-comments.views.layout :as layout]))

(defn post-create []
  (layout/common "Hello world"
                 [:h2 "Hi there"]))

(defroutes routes
  (GET "/posts/create" [] (post-create)))