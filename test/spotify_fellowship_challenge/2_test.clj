(ns spotify-fellowship-challenge.2-test
  (:require [spotify-fellowship-challenge.2 :as two]
            [clojure.test :refer :all]))

(deftest handles-one-char-strings
  (is (= (two/decode-string "1[x]") "x"))
  (is (= (two/decode-string "5[x]") "xxxxx")))

(deftest handles-unnested-strings
  (is (= (two/decode-string "1[ab]") "ab"))
  (is (= (two/decode-string "4[ab]") "abababab")))

(deftest handles-nested-strings
  (is (= (two/decode-string "2[b3[a]]") "baaabaaa")))
