(ns blog-comments.models.comment
  (:use [blog-comments.models.base :only (db)])
  (:require [clojure.java.jdbc :as sql]))

(defn all []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from comments order by id desc"]
      (into [] results))))

(defn create [body author]
  (sql/with-connection db
    (sql/insert-values :comments [:body :author] [body author])))