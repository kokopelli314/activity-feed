(defproject activity-feed "0.1.0"
  :description "Live GitHub public pull request feed"
  :url "https://nathanclonts.com/pull-requests"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
								 [compojure "1.6.1"]
								 [ring/ring-jetty-adapter "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
								 [hiccup "1.0.5"]
								 [clj-http "3.9.1"]
								 [cheshire "5.8.1"]
								 [markdown-clj "1.0.5"]
								 [environ "1.1.0"]]
	:plugins [[lein-ring "0.12.4"]
						[lein-environ "1.1.0"]]
  :ring {:handler activity-feed.handler/app}
  :profiles
		{:dev {:env {:base-url "/"}
					 :dependencies [[javax.servlet/servlet-api "2.5"]
													[ring/ring-mock "0.3.2"]]}
		 :prod {:env {:base-url "/activity-feed"}}}
	:main activity-feed.handler
	:aot [activity-feed.handler])
