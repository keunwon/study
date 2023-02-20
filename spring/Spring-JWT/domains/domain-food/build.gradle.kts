description = ""

dependencies {
    api(project(":core-jpa"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    testFixturesImplementation(project(":modules:inmemory-jpa-repository"))
}
