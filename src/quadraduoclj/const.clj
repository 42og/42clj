(ns quadraduoclj.const
  "Constant namespace.")

(def ^:public base-url
  "42.fr API base url"
  "https://api.intra.42.fr/v2/")

(def ^:public *auth-url* "https://api.intra.42.fr/oauth/authorize")
(def ^:public *access-token-uri* "https://api.intra.42.fr/oauth/token")
(def ^:public *api-scopes* "public")

(def ^:public oauth2-params
  "Oauth2 Configurations.
  State scope should be an
  ungessable string to avoid
  site forgery."
  {:client-uid (env :42client-uid)
   :client-secret (env :42client-secret)
   :authorize-uri *auth-url*
   :redirect-uri (env :42client-redirect-uri)
   :access-token-uri *access-token-uri*
   :state (random-state :len 40)
   :scope *api-scopes*})