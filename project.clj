(defproject activity-feed "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
								 [hiccup "1.0.5"]
								 [clj-http "3.9.1"]
								 [cheshire "5.8.1"]
								 [markdown-clj "1.0.5"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler activity-feed.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
