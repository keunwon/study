plugins {
    kotlin("jvm") version "1.6.21"
}

group = "com.keunwon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
    //testImplementation("io.kotest:kotest-property:5.5.5")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
