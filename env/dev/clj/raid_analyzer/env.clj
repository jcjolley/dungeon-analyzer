(ns raid-analyzer.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [raid-analyzer.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[raid-analyzer started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[raid-analyzer has shut down successfully]=-"))
   :middleware wrap-dev})
