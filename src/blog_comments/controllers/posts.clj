(ns blog-comments.controllers.posts
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [blog-comments.views.layout :as layout]
            [blog-comments.views.posts :as view]
            [blog-comments.models.post :as post]
            [ring.util.response :as response]))

(defn post-create []
  (view/post-create))

(defn post-create! [params]
  (let [name (:name params)]
    (post/create! name))
  (response/redirect "/"))

(defn posts-all []
  (view/posts-all (post/all)))

(defroutes routes
  (GET "/posts/all" [] (posts-all))
  (GET "/posts/create" [] (post-create))
  (POST "/posts/create" {params :params} (post-create! params)))