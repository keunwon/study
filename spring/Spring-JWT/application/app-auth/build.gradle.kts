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

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.security:spring-security-oauth2-client")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation(project(":domains:domain-user-auth"))
    testImplementation(project(":modules:restdocs-kotlin-dsl"))
    testImplementation(testFixtures(project(":domains:domain-user-auth")))
}
