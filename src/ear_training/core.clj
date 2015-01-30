(ns ear-training.core
  (:use [overtone.live]
        [overtone.synth.sampled-piano]
        [clojure.core.match :refer [match]]))

(def INTERVALS
  {:P1 0
   :m2 1
   :M2 2
   :m3 3
   :M3 4
   :P4 5
   :P5 6
   :A4 7
   :m6 8
   :M6 9
   :m7 10
   :M7 11
   :P8 12})

(def levels
  {1 [:P1 :M2 :M3 :P5]
   2 [:P1 :m2 :3 :P4]
   3 [:P1 :m2 :M2 :m3 :M3 :P4 :P5]})

(defn play-interval 
  ([tonic interval play-dur between-dur]
   (let [n (if (keyword? tonic) (note tonic) tonic)
         i (if (keyword? interval) (get INTERVALS interval) interval)
         cur-time (now)
         play-fn (fn [n end]
                   (let [s (sampled-piano n)]
                     (at end (ctl s :gate 0))))]
     (play-fn n (+ cur-time play-dur))
     (at (+ (now) between-dur) (play-fn (+ i n) (+ cur-time play-dur between-dur 40)))))
  ([tonic interval]
   (play-interval tonic interval 500 1000)))

(defn test-interval [tonic interval]
  (play-interval tonic interval)
  (match (keyword (read-line))
         interval true
         :g interval
         :else (test-interval tonic interval)))

(defn tri "Test Random Interval"
  [& {:keys [octave the-key from] :or {octave 4 the-key nil from (keys INTERVALS)}}]
  (let [k (if (nil? the-key) (choose (vals REVERSE-NOTES)) the-key)
        from (if (integer? from) (get levels from) from)
        interval (choose from)]
    (test-interval (keyword (str (name k) octave)) interval)))
