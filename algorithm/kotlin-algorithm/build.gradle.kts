plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.detekt)
}

allprojects {
    group = "com.keunwon"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        implementation(kotlin("stdlib"))

        implementation(rootProject.libs.feign.jackson)
        implementation(rootProject.libs.feign.core)
        implementation(rootProject.libs.feign.kotlin)
        implementation(rootProject.libs.jackson.module.kotlin)

        testImplementation(rootProject.libs.kotest.runner.junit5)
        testImplementation(rootProject.libs.kotest.framework.datatest)
        testImplementation(rootProject.libs.assertj.core)
        testImplementation(rootProject.libs.junit.jupiter.params)
    }
}
