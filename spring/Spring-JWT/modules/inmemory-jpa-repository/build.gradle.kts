tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}