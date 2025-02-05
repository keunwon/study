plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.keunwon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(libs.feign.jackson)
    implementation(libs.feign.core)
    implementation(libs.feign.kotlin)
    implementation(libs.jackson.module.kotlin)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.framework.datatest)
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter.params)
}

tasks {
    test {
        useJUnitPlatform()
    }
}
