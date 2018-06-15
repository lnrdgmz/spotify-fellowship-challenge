(ns spotify-fellowship-challenge.1-test
  (:require spotify-fellowship-challenge.1
            [clojure.test :refer :all]))


(deftest sort-with-ord-therapyw
  (is (= (spotify-fellowship-challenge.1/sort-by-string "weather" "therapyw") "theeraw")))

(deftest sort-with-ord-odg
  (is (= (spotify-fellowship-challenge.1/sort-by-string "good" "odg") "oodg")))
