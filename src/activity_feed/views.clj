(ns activity-feed.views
  (:require [clojure.string :as str]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))


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
    [:p "Latest activity from GitHub."]))