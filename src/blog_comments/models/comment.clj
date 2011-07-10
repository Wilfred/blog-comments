(ns blog-comments.models.comment
  (:use [blog-comments.models.base :only (db)])
  (:require [clojure.java.jdbc :as sql]))

(defn all []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from comments order by id desc"]
      (into [] results))))

(defn approved []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from comments where approved = true order by id desc"]
      (into [] results))))

(defn unapproved []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from comments where approved = false order by id desc"]
      (into [] results))))

(defn approve! [id]
  (try
    (sql/with-connection db
      (sql/transaction
       (sql/update-values :comments
                          ["id = ?" id]
                          {:approved true})))
    (catch Exception e
      (do (println e)
          (let [cause (.getCause e)]
            (do (println cause)
                (println (.getNextException cause))))))))

(defn create [body author]
  (sql/with-connection db
    (sql/insert-values :comments [:body :author] [body author])))