(ns blog-comments.controllers.comments
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [blog-comments.views.comments :as view]
            [blog-comments.models.comment :as model]))

(defn index []
  (view/index (model/all)))

(defn create [params]
  (let [body (:body params)
        author (:author params)]
    (when-not (and (str/blank? body) (str/blank? author))
      (model/create body author)))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" {params :params} (create params)))