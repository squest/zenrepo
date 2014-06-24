(ns zenrepo.content
  (:require [zenrepo.layout :as view]
            [me.raynes.fs :as fs]
            [clojure.string :as cs]))



(def repo (first (fs/find-files fs/*cwd* #"repo")))

(defn find-repo
  [zenid]
  (first (fs/find-files* repo #(= (str zenid) (fs/base-name %)))))

(defn all-files-in-repo
  [zenid]
  (first (fs/iterate-dir (find-repo zenid))))

(defn render
  [zenid]
  (let [data (all-files-in-repo zenid)
        paths (drop-while #(not= "repo" %)
                          (fs/split (first data)))
        path (cs/join "/" paths)
        files (filter #(= ".html" (fs/extension %))
                      (nth data 2))]
    (view/render (second (map #(str path "/" %)  files)))))




