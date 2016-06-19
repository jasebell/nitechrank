(ns nitechrank.core
  (:require [clojure.string :as str]
            [clj-time.core :as t]
            [clj-time.format :as f])
  (:gen-class))

(def base-number 52)
(def filepath "/Users/jasonbell/work/nitechrank/nitechrank.csv")

(def queries ["hadoop" "java" "php" "perl" "python" "ruby" "ios" "c++"])

(defn get-last-reading [filepath]
  (-> (slurp filepath)
      (str/split #"\n")
      last
      (str/split #",")
      last))

(defn pull-data [query]
  (->> (slurp (format "http://www.nijobs.com/ShowResults.aspx?Keywords=%s&Location=&Category=3&Recruiter=Company" query))
       (re-find #"Total Jobs Found: \d+")))

(defn calc-index [today]
  (double (* 100 (/ today base-number))))

(defn run-queries []
  (apply + (mapv (fn [query] (-> (pull-data query)
                                 (str/split #":")
                                 last
                                 (.trim)
                                 (Integer/parseInt))) queries)))
(defn gen-output []
  (let [today (run-queries)
        last-reading (java.lang.Double/valueOf (get-last-reading filepath))
        today-index (calc-index today)
        chg (double (- today-index 100))
        prev-diff (- chg last-reading)
        output (str (f/unparse (f/formatters :date) (t/now))
                    ","
                    today
                    ","
                    today-index
                    ","
                    chg "\n")
        tweet (str "#nitechrank " (f/unparse (f/formatters :date) (t/now))
                   " Index:" today-index
                   " Change:" chg "\n"
                   " PrevDiff:" prev-diff)]
    (spit filepath output :append true)
    (println tweet)))

(defn -main
  [& args]
  (gen-output))
