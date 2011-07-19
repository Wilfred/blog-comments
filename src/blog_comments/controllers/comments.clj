(ns blog-comments.controllers.comments
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as response]
            [blog-comments.views.comments :as view]
            [blog-comments.models.comment :as comment]))

(defn comments-all []
  (view/comments-all (comment/approved) (comment/unapproved)))

; note this will break if the URL contains something we can't convert to a int
(defn comment-approve [id]
  (comment/approve! (Integer/parseInt id))
  (response/redirect "/comments/all"))

(defn comment-unapprove [id]
  (comment/unapprove! (Integer/parseInt id))
  (response/redirect "/comments/all"))

(defn comment-create []
  (view/comment-create))

(defn comment-create! [params]
  (let [body (:body params)
        author (:author params)]
    (when-not (and (str/blank? body) (str/blank? author))
      (comment/create body author)))
  (response/redirect "/"))

(defroutes routes
  (GET  "/" [] (response/redirect "/comments/all"))

  (GET  "/comments/all" [] (comments-all))
  (GET  "/comments/create" [] (comment-create))
  (GET  "/comments/:id/approve" [id] (comment-approve id))
  (GET  "/comments/:id/unapprove" [id] (comment-unapprove id))

  (POST "/" {params :params} (comment-create! params)))