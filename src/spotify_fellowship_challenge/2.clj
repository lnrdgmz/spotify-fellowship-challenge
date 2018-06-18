(ns spotify-fellowship-challenge.2
  (:require [clojure.string :as str]))

;; We'll iterate through the encoded string and build up a stack of strings that
;; will be expanded later. Let's define some helper functions first.

(defn- repeat-string [num s]
  (str/join (repeat num s)))

;; When we find a number and an open bracket, we'll add that number and the
;; corresponding string, if any, to the stack.

(defn- add-to-stack
  [stack opening-bit]
  (let [vals (str/split opening-bit #"\[")
        n (Integer/parseInt (first vals))
        s (nth vals 1 "")]
    (conj stack {:n n :s s})))

;; When we hit a closing bracket, we'll expand the corresponding data (the end
;; of the stack). Nested strings need to be expanded further, so we add the
;; newly expanded string to the next item in the stack. If the stack has only
;; one item (or none), there's nothing to do, so we just return it unchanged.

(defn- consolidate-stack
  [stack]
  (if (<= (count stack) 1)
    stack
    (let [decoded (repeat-string ((last stack) :n) ((last stack) :s))
          second-from-last (get stack (- (count stack) 2))
          new-end (update second-from-last :s #(str (str % decoded)))]
      (conj (pop (pop stack)) new-end))))

;; Now we can define our function. We'll iterate through the string, adding
;; items to the stack when we encounter a number and a `[`, and expand strings
;; when we hit a `]`

(defn decode-string
  [s]
  "Decodes a string `s` encoded in the format k[encoded_string]"
  (loop [stack []
         remaining s]
    (if (empty? remaining)
      (repeat-string (get (first stack) :n) (get (first stack) :s))
      (let [opening (re-find #"^\d+\[[a-zA-Z]*" remaining)]
        (if opening
          (recur (add-to-stack stack opening)
                 (subs remaining (count opening)))
          (recur (consolidate-stack stack)
                 (subs remaining 1)))))))
