import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

group = "com.effective"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

object Versions {
    const val coroutineVersion = "1.6.3"
    const val ankoVersion = "0.10.4"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}")

    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.4.0")
    testImplementation("io.kotest:kotest-assertions-core:5.4.0")
    testImplementation("io.kotest:kotest-property:5.4.0")
    implementation(kotlin("script-runtime"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
