tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

val jwtVersion by extra { "0.11.5" }

dependencies {
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-security")

    // jwt
    api("io.jsonwebtoken:jjwt-api:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")

    testApi("org.springframework.boot:spring-boot-starter-test")
    testApi("org.springframework.security:spring-security-test")

    api(project(":core-jpa"))
    api(project(":domains:domain-user"))
    testApi(testFixtures(project(":domains:domain-user")))
    testFixturesApi(testFixtures(project(":domains:domain-user")))
    testFixturesApi(project(":modules:inmemory-jpa-repository"))
}
