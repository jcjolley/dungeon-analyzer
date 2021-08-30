(ns raid-analyzer.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[raid-analyzer started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[raid-analyzer has shut down successfully]=-"))
   :middleware identity})
