(ns blog-comments.models.migration
  (:use [blog-comments.models.base :only (db)])
  (:require [clojure.java.jdbc :as sql]))

(defn create-comments-table []
  (sql/with-connection db
    (try
      (sql/drop-table :shouts)
      (catch Exception _))
    (try
      (sql/drop-table :comments)
      (catch Exception _))
    (sql/create-table :comments
                      [:id :serial "PRIMARY KEY"]
                      [:body :varchar "NOT NULL"]
                      [:author :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])
    ))

(defn -main []
  (print "Migrating database...") (flush)
  (create-comments-table)
  (println " done"))