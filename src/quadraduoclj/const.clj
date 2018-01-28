(ns quadraduoclj.const
  "Constant namespace.")

(def ^{:dynamic true} base-url ;; Basename
  "https://api.intra.42.fr/v2/")

(def ^{:dynamic true} *auth-url* "https://api.intra.42.fr/oauth/authorize")
(def ^{:dynamic true} *access-token-uri* "https://api.intra.42.fr/oauth/token")
(def ^{:dynamic true} *api-scopes* "public")
