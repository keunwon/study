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

    // webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // resilience
    implementation("io.github.resilience4j:resilience4j-spring-boot2")

    // cloud
    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-gateway-webflux")

    // blockhound
    implementation("io.projectreactor.tools:blockhound:1.0.6.RELEASE")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug")
}

configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-web")
    }
}

tasks.withType<BootJar> {
    archiveBaseName.set("sre-webflux")
}
