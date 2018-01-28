(ns quadraduoclj.consumer-test
  (:require [clojure.test :refer :all]
            [quadraduoclj.oauth2 :refer :all]))

(deftest protocol-instance
  (testing "The factory should be instanciated corrctly."
    (let [nonce (java.util.UUID/randomUUID)
          csm (make-consumer
               :client-uid "foo"
               :client-secret "bar"
               :authorize-uri "http://bullshit.org"
               :redirect-url "localhost"
               :scope "admin"
               :state nonce)]
      (is (= (:client-uid "foo"))))))
