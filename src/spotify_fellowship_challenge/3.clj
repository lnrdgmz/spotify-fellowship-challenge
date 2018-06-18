(ns spotify-fellowship-challenge.3)

;; The function:
;; - considers the last denomination in the vector, then
;; - counts up from 0, denoting the number of this denomination of coins which
;;   will fit in `amount`, and
;; - collects the result of recursing with the remaining `amount` and `denominations`
;;
;; `0` is returned if there are no denominations of coins remaining to make up the
;; amount. `1` is returned if `amount` is `0`

(defn change-possibilities
  [amount denominations]
  (cond (and (> amount 0) (empty? denominations)) 0
        (= amount 0) 1
        :else
        (loop [coins 0
               result 0]
          (if (< amount (* coins (last denominations)))
            result
            (recur (inc coins)
                   (+ result
                      (change-possibilities
                       (- amount (* coins (last denominations)))
                       (pop denominations)))))))
  )
