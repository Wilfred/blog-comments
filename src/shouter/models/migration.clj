(ns shouter.models.migration
  (:use [shouter.models.base :only (db)])
  (:require [clojure.java.jdbc :as sql]))

(defn create-comments-table []
  (sql/with-connection db
    (sql/create-table :comments
                      [:id :serial "PRIMARY KEY"]
                      [:body :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])
    (try
      (sql/drop-table :shouts)
      (catch Exception _))))

(defn -main []
  (print "Migrating database...") (flush)
  (create-comments-table)
  (println " done"))