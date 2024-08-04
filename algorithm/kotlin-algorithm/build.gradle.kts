plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.keunwon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
    testImplementation("org.assertj:assertj-core:3.26.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0-M2")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
