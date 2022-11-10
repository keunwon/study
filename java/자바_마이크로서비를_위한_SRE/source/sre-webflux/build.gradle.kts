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
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    runtimeOnly("org.springframework.cloud:spring-cloud-sleuth-zipkin")
    runtimeOnly("org.springframework.cloud:spring-cloud-starter-sleuth")
}

configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-web")
    }
}

tasks.withType<BootJar> {
    archiveBaseName.set("sre-webflux")
}
