rootProject.name = "jwt"
include("application:app-auth")
include("domains:domain-user")
include("core")
include("domains:domain-user-auth")
include("modules:inmemory-jpa-repository")
include("core-jpa")
include("modules:restdocs-kotlin-dsl")
