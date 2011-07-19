(ns blog-comments.core
  (:use [compojure.core :only [defroutes]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [blog-comments.controllers.comments]
            [blog-comments.controllers.posts]
            [blog-comments.views.layout :as layout]))

(defroutes routes
  blog-comments.controllers.comments/routes
  blog-comments.controllers.posts/routes
  (route/resources "/") ; static file handlers
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty (var application) {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))