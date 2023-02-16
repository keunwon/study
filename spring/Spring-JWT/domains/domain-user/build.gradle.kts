description = "사용자 회원 생성 및 관리"

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    api(project(":core-jpa"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    //runtimeOnly("com.h2database:h2")
    testFixturesApi(project(":modules:inmemory-jpa-repository"))
}
