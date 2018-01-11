(ns quadraduoclj.request-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as client]))

(deftest request-test
  (testing "Testing request"
    (let [hc (client/get "https://api.intra.42.fr/v2/topics/4242/messages"
                         {:content-type :json})]
      (is (= (:error hc) "Not authorized")))))