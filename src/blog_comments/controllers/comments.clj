(ns blog-comments.controllers.comments
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [blog-comments.views.comments :as view]
            [blog-comments.models.comment :as model]))

(defn comments-all []
  (view/comments-all (model/approved) (model/unapproved)))

; note this will break if the URL contains something we can't convert to a int
(defn comment-approve [id]
  (model/approve! (Integer/parseInt id))
  (ring/redirect "/comments/all"))

(defn create [params]
  (let [body (:body params)
        author (:author params)]
    (when-not (and (str/blank? body) (str/blank? author))
      (model/create body author)))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (ring/redirect "/comments/all"))
  (GET  "/comments/all" [] (comments-all))
  (GET  "/comments/:id/approve" [id] (comment-approve id))
  (POST "/" {params :params} (create params)))