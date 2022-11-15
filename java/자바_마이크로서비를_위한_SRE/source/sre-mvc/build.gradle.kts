import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    kotlin("jvm") version "1.6.21"
}

group = "com.spring"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":sre-core"))
    implementation(kotlin("stdlib"))
}

tasks.withType<BootJar> {
    archiveBaseName.set("sre-mvc")
}
