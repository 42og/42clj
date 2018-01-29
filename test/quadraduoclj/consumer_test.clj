(ns quadraduoclj.consumer-test
  "Consumer instance testing suite"
  (:require [clojure.test :refer :all]
            [quadraduoclj.oauth2 :refer :all]))

(deftest protocol-instance
  (testing "The factory should be instanciated corrctly."
    (let [nonce (java.util.UUID/randomUUID)
          csm (#'quadraduoclj.oauth2/make-consumer
               {:client-uid "foo"
                :client-secret "bar"
                :authorize-uri "http://bullshit.org"
                :redirect-url "localhost"
                :scope "admin"
                :state nonce})]
      (is (= (:client-uid csm) "foo"))
      (is (= (:client-secret csm) "bar"))
      (is (= (:authorize-uri csm) "http://bullshit.org"))
      (is (= (:scope csm) "admin"))
      (is (= (:state csm) nonce)))))

(deftest format-auth-url-test
  (testing "Url should be corretly generated from records"
    (let [csm (#'quadraduoclj.oauth2/make-consumer
               {:client-uid "foo"
                :client-secret "bar"
                :authorize-uri "http://bullshit.org"
                :redirect-url "localhost"
                :scope "admin"
                :state "1337-h4x0rz"})
          auth-endpoint (#'quadraduoclj.oauth2/format-auth-url csm)]
      (is (= auth-endpoint
             "https://api.intra.42.fr/oauth/authorize?client_id=foo&redirect_uri=localhost&response_type=code&scope=admin&state=1337-h4x0rz")))))
