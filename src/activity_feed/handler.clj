(ns activity-feed.handler
  (:require [activity-feed.views :as views]
            [compojure.core :refer :all]
            [compojure.route :as route]
						[ring.middleware.defaults :refer [wrap-defaults site-defaults]]
						[ring.adapter.jetty :as jetty]
						[hiccup.middleware :refer [wrap-base-url]]
						[environ.core :refer [env]])
	(:gen-class))

(defroutes app-routes
	(GET "/"
		[]
		(views/home-page))
	(route/resources "/")
	(route/not-found "Not Found"))

(def app
	(wrap-base-url
		(wrap-defaults app-routes site-defaults) (env :base-url)))

(defn -main
	[& [port]]
	(let [port (Integer. (or port (System/getenv "PORT") 5000))]
		(jetty/run-jetty #'app {:host "127.0.0.1"
														:port port
														:join? false})))

(println (str "Base URL: " (env :base-url)))
