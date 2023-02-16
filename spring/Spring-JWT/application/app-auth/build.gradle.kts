description = "사용자 회원 가입 & 관리"

dependencies {
    implementation("org.springframework.security:spring-security-oauth2-client")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation(project(":domains:domain-user-auth"))
    testImplementation(project(":modules:restdocs-kotlin-dsl"))
    testImplementation(testFixtures(project(":domains:domain-user-auth")))
}
