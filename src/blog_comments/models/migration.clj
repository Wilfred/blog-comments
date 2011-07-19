(ns blog-comments.models.migration
  (:use [blog-comments.models.base :only (db)])
  (:require [clojure.java.jdbc :as sql]))

(defn create-comments-table []
  (sql/with-connection db
    

    (sql/drop-table :comments)
    (sql/drop-table :posts)

    (sql/create-table :posts
                        [:id :serial "PRIMARY KEY"]
                        [:name :varchar "NOT NULL"])

    (sql/create-table :comments
                      [:id :serial "PRIMARY KEY"]
                      [:post_id :integer "REFERENCES posts(id)"]
                      [:body :varchar "NOT NULL"]
                      [:author :varchar "NOT NULL"]
                      [:approved :boolean "NOT NULL" "DEFAULT false"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (print "Migrating database...") (flush)
  (create-comments-table)
  (println " done"))