(ns spotify-fellowship-challenge.1
  (:require [clojure.string :as str]))

;; We can use the built-in `sort` function with a custom comparator. Our
;; `make-compatator` function will return a function that will compare two
;; characters based on their position in the string `ord`.

(defn- make-comparator
  "Returns a comparator function based on the specified ordering `ord`."
  [ord]
  (fn [a b]
    (compare (str/index-of ord a)
             (str/index-of ord b))))

(defn sort-by-string [str ord]
  (let [comparator (make-comparator ord)]
    (str/join (sort comparator str))))


;; This approach is slightly inefficient, with a time complexity of
;; O(n^2*log(n)). The `sort` function is O(nlog(n)), but the comparator function
;; is linear, because it uses `index-of` to compare individual characters.
;;
;; The current code works acceptably for the given examples, but if we needed
;; improved performance with regard to time, we can make a constant-time
;; comparator. By iterating through `ord` once, we can build up a hash table
;; with char/index pairs. Our comparator could then compare characters in
;; constant time, making `sort-by-string` have a time complexity of O(nlog(n)).
