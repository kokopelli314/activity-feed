(ns activity-feed.handler
  (:require [activity-feed.views :as views]
            [compojure.core :refer :all]
            [compojure.route :as route]
						[ring.middleware.defaults :refer [wrap-defaults site-defaults]]
						[ring.adapter.jetty :as jetty])
	(:gen-class))

(defroutes app-routes
	(GET "/"
		[]
		(views/home-page))
	(route/resources "/")
	(route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main
	[& [port]]
	(let [port (Integer. (or port (System/getenv "PORT") 5000))]
		(jetty/run-jetty #'app {:port port
														:join? false})))
