(ns quadraduoclj.oauth2
  "Authentification related namespace."
  (:require [clj-http.client :as client]
            [clj-http.util :as utils]
            [quadraduoclj.const :refer :all]))

(def token
  (atom {})) ;; Should be updatable

(defrecord ^{:private true} Consumer
    [client-uid client-secret
     authorize-uri redirect-uri
     scope state])

(defn ^{:public true} make-consumer
  [{:keys [client-uid client-secret authorize-uri
           & redirect-url scope state]
   :or {scope "public"
        redirect-url "https://intra.42.fr"
        state (str (java.util.UUID/randomUUID))}}]
  {:pre [(string? client-uid)]}
  (let [csm (Consumer. client-uid client-secret authorize-uri
                       redirect-url scope state)]
    csm))

(defn ^{:private false} format-auth-url
  "Process Authorization For User.
  Redirect Users To Request 42 Access.
  Small Note: When Formatting The Scopes
  Parameters, Be Sure To Read Above About
  The Distinction Between Application-Level
  And Token-Level Scopes. This Has Been
  A Point Of Friction For Some Developers."
  [{:keys [client-id redirect-uri scope state]
    :or {redirect-uri "http://intra.42.fr"
         scope "public"
         state (str (java.util.UUID/randomUUID))}}]
  (str (format "%s?client_id=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s"
               *auth-url* client-id
               (str (utils/url-encode
                     redirect-uri))
               scope state)))
