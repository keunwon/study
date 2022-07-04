import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

group = "me.developer"
version = "1.0-SNAPSHOT"

val exposedVersion = "0.37.3"

repositories {
    mavenCentral()
}


dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")

    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.3.0")
    testImplementation("io.kotest:kotest-assertions-core:5.3.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
