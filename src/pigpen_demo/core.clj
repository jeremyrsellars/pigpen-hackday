(ns pigpen-demo.core
  (:require [pigpen.core :as pig]))

; (spit "input.tsv" "1\t2\tfoo\n4\t5\tbar")

(defn my-data []
  ;(pig/load-tsv "input.tsv")
  (pig/load-json "..\\test-1.json")
  )

(pig/load-json "..\\test-1.json")

(my-data)

(defn my-data-1 []
  (->>
    (pig/load-tsv "input.tsv")
    (pig/map (fn [[a b c]]
               {:sum (+ (Integer/valueOf a) (Integer/valueOf b))
                :name c}))))
(defn my-data-2 []
  (->>
    (pig/load-tsv "input.tsv")
    (pig/map (fn [[a b c]]
               {:sum (+ (Integer/valueOf a) (Integer/valueOf b))
                :name c}))
    (pig/filter (fn [{:keys [sum]}]
                  (< sum 5)))))

(defn load-json-vec [input-file]
  (pig/load-json input-file))

(defn my-func [data]
  (->> data
    (pig/map (fn [[a b c]]
               {:sum (+ (Integer/valueOf a) (Integer/valueOf b))
                :name c}))
    (pig/filter (fn [{:keys [sum]}]
                  (< sum 5)))))

(defn exams-per-patient [data]
  (->> data
    (pig/group-by :name)
    (pig/map (fn [[pat-name exams]] [pat-name (count exams)]))))

(defn my-query [input-file output-file]
  (->>
    (load-json-vec input-file)
    (exams-per-patient)
    (pig/store-clj output-file)))

;(pig/dump (my-data-2))

#_(-> (pig/dump
     (-> (my-data)
         exams-per-patient))
    )


#_(pig/write-script "my-script.pig" (my-query "input.tsv" "output.clj"))
