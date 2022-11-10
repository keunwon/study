import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    java.sourceCompatibility = JavaVersion.VERSION_17

    extra.set("springCloudVersion", "2021.0.5")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
        //implementation("io.micrometer:micrometer-registry-prometheus")
        runtimeOnly("io.micrometer:micrometer-registry-prometheus")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        implementation("org.springframework.boot:spring-boot-starter-log4j2")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    configurations {
        all {
            exclude("org.springframework.boot", "spring-boot-starter-logging")
        }
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.getByName<Jar>("jar") {
        enabled = false
    }
}
