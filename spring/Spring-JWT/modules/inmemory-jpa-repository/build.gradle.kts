allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}
