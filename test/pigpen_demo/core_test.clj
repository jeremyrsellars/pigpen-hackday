(ns pigpen-demo.core-test
  (:require [clojure.test :refer :all]
            [pigpen.core :as pig]
            [pigpen-demo.core :refer :all]))

(deftest test-my-func
  (let [data (pig/return [["1" "2" "foo"] ["4" "5" "bar"]])]
    (is (= (pig/dump (my-func data))
           [{:sum 3, :name "foo"}]))))
