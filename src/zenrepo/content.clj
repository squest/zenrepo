(ns zenrepo.content
  (:require [zenrepo.layout :as view]
            [me.raynes.fs :as fs]
            [clojure.string :as cs]))

(def repo (first (fs/find-files fs/*cwd* #"repo")))

(defn- find-repo
  [zenid]
  (first (fs/find-files* repo #(= (str zenid) (fs/base-name %)))))

(defn all-files-in-repo
  [zenid]
  (first (fs/iterate-dir (find-repo zenid))))

(defn- pages-list
  [zenid]
  (let [data (all-files-in-repo zenid)
        paths (drop-while #(not= "repo" %)
                          (fs/split (first data)))
        path (cs/join "/" paths)
        files (filter #(= ".html" (fs/extension %))
                      (nth data 2))]
    (map #(str path "/" %)  files)))

(defn pages
  [zenid]
  (let [files (pages-list zenid)]
    (map #(hash-map :url (str "/rendered-content/" zenid "/" (fs/base-name %))
                    :filename %)
         files)))






