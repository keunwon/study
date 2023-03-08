plugins {
    kotlin("jvm") version "1.6.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotestVersion by extra { "5.5.5" }
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}
