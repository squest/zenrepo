(ns zenrepo.routes.home
  (:use compojure.core)
  (:require [zenrepo.layout :as view]
            [zenrepo.content :as content]))

(defn home-page
  []
  (view/render "home.html"
               {:page {:title "Demo site"
                       :headline "Please browse the content"}
                :content (content/get-all-content)}))

(defroutes home-routes
  (GET "/" [] (home-page)))
