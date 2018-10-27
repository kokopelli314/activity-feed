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

(defn get-repo-link [pr]
	(let [url (get pr "html_url")
				text (nth (re-find #"github\.com\/repos\/(.*)\/issue" (get pr "url")) 1)]
		[:a {:href url :target "_blank"} text]))

(defn make-pull-request-html [pr]
	[:div {:class "pull-request-wrapper"}
		[:p (get-repo-link pr) (str ": " (get pr "title"))]])

(defn section-pull-requests [prs]
	"Returns markup for first 3 PRs."
	(map make-pull-request-html (take 3 prs)))


(defn gen-page-head
  [title]
  [:head
		[:title "Activity Feed"]
		(page/include-css "/css/normalize.css")
		(page/include-css "/css/skeleton.css")
    (page/include-css "/css/styles.css")])

(defn home-page
  []
  (page/html5
		(gen-page-head "Bonkers")
			[:div {:class "content six columns offset-by-three"}
				[:div {:class "row"}
					[:div {:class "six columns"}
						[:h1 {:class "row"} "Open Source"]
						[:p "Latest public pull requests on GitHub"]]
					[:div {:class "six columns"}
						[:a {:href "https://github.com/kokopelli314" :target "_blank"}
							[:img
								{:src "https://avatars0.githubusercontent.com/u/10605105"
								:max-width "150px"
								:class "profile-image"}]]]]
				[:div {:class "pull-request-section"}
					(section-pull-requests (get-pull-request-data))]]))
