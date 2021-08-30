(ns raid-analyzer.parser
  (:require
   [clojure.string :as str]
   [java-time :as jt]))


(defn parse-time [time-str]
  (let [year (jt/as (jt/local-date) :year)
        time-str-including-year (str year "/" time-str)
        format "yyyy/M/dd HH:mm:ss.SSS"]
    (jt/local-date-time format time-str-including-year)))

(defn parse-spell-cast-success [event-parts]
  (zipmap [:subevent :source-guid :source-name :source-flags :source-raid-flags :dest-guid :dest-name :dest-flags :dest-raid-flags :spell-id :spell-name :unknown-flags :info-guid :owner-guid :current-hp :max-hp :attack-power :spell-power :armor :absorb :power-type :current-power :max-power :power-cost :position-x :position-y :ui-map-id :facing :level ] event-parts))

(defn parse-subevent [event-parts]
  (let [subevent (first event-parts)]
    (cond
      (= "SPELL_CAST_SUCCESS" subevent) (parse-spell-cast-success event-parts))))

(defn parse-log [entry]
  (let [[time events-str] (str/split entry #"\s{2}")
        event-parts (str/split events-str #",")]
    (merge {:time (parse-time time)} (parse-subevent event-parts))))

(defn read-file [filename]
  (->> (slurp filename)
       str/split-lines))

#_(->> (read-file "resources/input/sample-log.txt")
       (filter #(str/includes? % "SPELL_CAST_SUCCESS"))
       (take 1)
       (map parse-log))
