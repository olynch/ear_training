(ns ear-training.solfege
  (:require [overtone.live :refer :all]
            [overtone.inst.sampled-piano :refer :all]
            [clojure.core.match :refer [match]]))

(def fifths [:i :v :ii :vi :iii :vii :iv])
(def degree->solfege
  {:i "do"
   :ii "re"
   :iii "mi"
   :iv "fa"
   :v "sol"
   :vi "la"
   :vii "si"})

(defn play-rand-note [notes]
  (let [degree (choose notes)
        note (-> degree (degree->interval :major) (+ 24 (* 12 (rand-int 6))))
        p (sampled-piano note)]
    (at (+ (now) 500) (ctl p :gate 0))
    [(degree degree->solfege) note]))

(defn keyboard-practice [notes]
  (let [solfege-note (play-rand-note notes)]
    (match (read-line)
           "" (do
                (println solfege-note)
                (keyboard-practice notes))
           "q" nil)))
