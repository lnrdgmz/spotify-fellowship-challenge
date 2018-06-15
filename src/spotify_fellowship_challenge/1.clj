(ns spotify-fellowship-challenge.1
  (:require [clojure.string :as str]))

(defn make-comparator [ord]
  "Returns a comparator function based on the specified ordering `ord`."
  (fn [a b]
    (compare (str/index-of ord a)
             (str/index-of ord b))))

(defn sort-by-string [str ord]
  (let [comparator (make-comparator ord)]
    (str/join (sort comparator str))))
