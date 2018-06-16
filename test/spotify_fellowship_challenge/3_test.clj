(ns spotify-fellowship-challenge.3-test
  (:require [spotify-fellowship-challenge.3 :as three]
            [clojure.test :refer :all]))

(deftest handles-example
  (is (= (three/change-possibilities 4 [1 2 3]) 4)))

(deftest handles-cases-with-one-denomination
  (is (= (three/change-possibilities 1 [1]) 1))
  (is (= (three/change-possibilities 9 [3]) 1))
  (is (= (three/change-possibilities 10 [3]) 0)))

(deftest handles-denominations-larger-than-amount
  (is (= (three/change-possibilities 5 [10 5 1]) 2))
  (is (= (three/change-possibilities 3 [10 4]) 0)))
