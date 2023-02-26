description = "배달 API"

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation(project(":domains:domain-food"))
    testImplementation(testFixtures(project(":domains:domain-food")))
}
