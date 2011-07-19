(ns blog-comments.models.post
  (:use [blog-comments.models.base :only (db)])
  (:require [clojure.java.jdbc :as sql]))

(defn create! [name]
  (sql/with-connection db
    (sql/insert-values :posts [:name] [name])))