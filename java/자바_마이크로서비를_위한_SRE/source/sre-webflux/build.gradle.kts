import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.6.21"
}

version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":sre-core"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation(kotlin("stdlib"))
}

configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-web")
    }
}

tasks.withType<BootJar> {
    archiveBaseName.set("sre-webflux")
}
