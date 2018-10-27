(ns activity-feed.views
  (:require [clojure.string :as str]
            [hiccup.page :as page]
						[ring.util.anti-forgery :as util]
						[clj-http.client :as client]
						[cheshire.core :as json]
						[markdown.core :refer [md-to-html-string]]))


(defn get-pull-request-data []
	"
	Returns vector of items containing PR data.
	"
	(let [response (client/get
									"https://api.github.com/search/issues?q=author%3Akokopelli314+type%3Apr&sort=updated"
									{:accept :json})]
		(get (json/parse-string (get response :body)) "items")))

(defn make-pull-request-html [pr]
	[:div
		[:h3 (get pr "title")]
		[:p (md-to-html-string (get pr "body"))]
		[:p (get pr "comments")]])

(defn section-pull-requests [prs]
	(map make-pull-request-html prs))


(defn gen-page-head
  [title]
  [:head
    [:title "Activity Feed"]
    (page/include-css "/css/styles.css")])

(defn home-page
  []
  (page/html5
    (gen-page-head "Bonkers")
    [:h1 "Open Source"]
    [:p "Latest activity from GitHub"]
		[:img
			{:src "https://avatars0.githubusercontent.com/u/10605105"
			 :width "200px"
			 :class "profile-image"}]
		[:div
			[:p (section-pull-requests (get-pull-request-data))]]))
