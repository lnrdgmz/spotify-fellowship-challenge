(ns spotify-fellowship-challenge.3)

;; We'll use a private function to implement the logic. This function will take
;; `demoninations` as a sorted set so that we can easily go through the coin values
;; from greatest to smallest.
;;
;; The function:
;; - considers the largest denomination available, then
;; - counts up from 0, denoting the number of this denomination of coins which
;;   will fit in `amount`
;; - collects the result of recursing with the remaining `amount` and `denominations`
;;
;; `0` is returned if there are no denominations of coins remaining to make up the
;; amount. `1` is returned if `amount` is `0`

(defn- change-possibilities-sorted
  [amount denominations]
  (cond (and (> amount 0) (empty? denominations)) 0
        (= amount 0) 1
        :else
        (loop [coins 0
               result 0]
          (if (< amount (* coins (first denominations)))
            result
            (recur (inc coins)
                   (+ result
                      (change-possibilities-sorted
                       (- amount (* coins (first denominations)))
                       (disj denominations (first denominations))))))))
  )

;; Our public function takes a vector of denominations, converting it into a
;; set, sorted in reverse order, and passing it to our private function.

(defn change-possibilities
  [amount denominations]
  (change-possibilities-sorted
   amount
   (apply (partial sorted-set-by >) denominations)))
