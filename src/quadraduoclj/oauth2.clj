(ns quadraduoclj.oauth2
  "Authentification related namespace."
  (:require [clj-http.client :as client]
            [clj-http.util :as utils]
            [quadraduoclj.const :refer :all]))

(def token
  (atom {:status nil}))

(defrecord ^{:private true} Consumer
    [client-uid client-secret
     authorize-uri redirect-uri
     scope state])

(defn ^{:private true} make-consumer
  "Consumer factory"
  [{:keys [client-uid client-secret authorize-uri
           & redirect-url scope state]
   :or {scope "public"
        redirect-url "https://intra.42.fr"
        state (str (java.util.UUID/randomUUID))}}]
  {:pre [(string? client-uid)]}
  (let [csm (->Consumer client-uid client-secret authorize-uri
                        redirect-url scope state)]
    csm))

(defn ^{:private false} format-auth-url
  "Process Authorization For User.
  Redirect Users To Request 42 Access."
  [consumer]
  {:pre [(record? consumer)]}
   (str (format "%s?client_id=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s"
               *auth-url* (:client-uid consumer)
               (str (utils/url-encode
                     (:redirect-uri consumer)))
               (:scope consumer)
               (:state consumer))))
