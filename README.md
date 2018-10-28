# activity-feed

Practice application to show my latest GitHub commits via a Clojure web server.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

```
lein with-profile dev ring server
```

To create a deployable JAR file, run:

```
lein uberjar
```
