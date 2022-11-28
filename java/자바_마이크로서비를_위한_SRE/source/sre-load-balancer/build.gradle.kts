plugins {
    kotlin("jvm") version "1.6.21"
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("io.micrometer:micrometer-registry-health:1.10.1")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
}

configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-web")
    }
}
