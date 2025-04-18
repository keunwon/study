plugins {
    alias(libs.plugins.kotlin.jvm)
}

allprojects {
    group = "com.keunwon"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "kotlin")

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

tasks {
    test {
        useJUnitPlatform()
    }
}
