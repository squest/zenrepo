(ns zenrepo.routes.home
  (:use compojure.core)
  (:require [zenrepo.layout :as view]
            [zenrepo.content :as content]))

(defn home-page
  []
  (view/render "html/home.html"
               {:page {:title "Demo site"
                       :headline "Please browse the content"}}))

(defn render-files
  [filename]
  (view/render (str "html/" filename)
               {:page {:title "Demo site"
                       :headline (str "this is the content from " filename)}}))


(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/content/:zenid" [zenid]
       (content/render zenid))
  (GET "/render/:filename" [filename]
       (render-files filename)))

