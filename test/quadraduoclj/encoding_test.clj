(ns quadraduoclj.encoding_test
  (:require [clojure.test :refer :all]
            [quadraduoclj.oauth2 :as oauth2]))

(deftest auth-url-test
  (testing "Checking if url is correctly encoded"
    (let [state (java.util.UUID/randomUUID)]
      (let [match (oauth2/format-auth-url
                   :client-id "wtf"
                   :client-secret "didido"
                   :redirect-uri "127.0.0.1"
                   :state state)
            result (str ("https://api.intra.42.fr/oauth/authorize?client_id=wtf&redirect_uri=127.0.0.1&response_type=code&scope=public&state=" state))]))))
