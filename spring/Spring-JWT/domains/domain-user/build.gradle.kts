tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    api(project(":core"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("com.h2database:h2")
    testFixturesApi(project(":modules:inmemory-jpa-repository"))
}
