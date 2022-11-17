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
    implementation("ca.pjer:logback-awslogs-appender:1.6.0")
    implementation(kotlin("stdlib"))
}

tasks.withType<BootJar> {
    archiveBaseName.set("sre-mvc")
}
