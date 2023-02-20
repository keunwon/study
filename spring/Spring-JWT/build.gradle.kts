plugins {
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.1.0"
    id("java-test-fixtures")
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("jacoco")
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    kotlin("kapt") version "1.7.22"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
    //java.sourceCompatibility = JavaVersion.VERSION_18
}

extra.apply {
    set("jwtVersion", "0.11.5")
    set("mockkVersion", "1.13.3")
    set("kotestVersion", "5.5.5")
    set("restAssuredVersion", "5.3.0")
}

// 폴더는 제외 (예: modules, domains, application)
val projectMatch by extra { """^core|^\w{2,}(-\w{2,})+$""".toRegex() }
val kotlinProject by extra { subprojects.filter { it.name.matches(projectMatch) } }
configure(kotlinProject) {
    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("java-test-fixtures")
        plugin("jacoco")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.kapt")
    }

    dependencies {
        api("org.jetbrains.kotlin:kotlin-reflect")
        api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        api("com.fasterxml.jackson.module:jackson-module-kotlin")
        api("org.springframework.boot:spring-boot-starter-log4j2")

        testImplementation("io.mockk:mockk:${property("mockkVersion")}")
        testImplementation("io.kotest:kotest-runner-junit5:${property("kotestVersion")}")
        testImplementation(kotlin("script-runtime"))

        modules {
            module("org.springframework.boot:spring-boot-starter-logging") {
                replacedBy("org.springframework.boot:spring-boot-starter-log4j2")
            }
        }
    }

    jacoco {
        toolVersion = "0.8.8"
        //reportsDirectory = layout.buildDirectory.dir("")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        test {
            useJUnitPlatform()
            testLogging { showCauses = true }
            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(test)
            reports {
                html.required.set(true)
                xml.required.set(false)
                csv.required.set(false)
            }

            val excludes = mutableListOf<String>()
            classDirectories.setFrom(
                sourceSets.main.get().output.asFileTree.matching { exclude(excludes) }
            )
            //finalizedBy(jacocoTestCoverageVerification)
        }

        jacocoTestCoverageVerification {
            violationRules {
                rule {
                    enabled = true
                    element = "CLASS"

                    limit {
                        counter = "BRANCH"
                        value = "COVEREDCOUNT"
                        minimum = "0.80".toBigDecimal()
                    }
                    limit {
                        counter = "LINE"
                        value = "TOTALCOUNT"
                        maximum = "200".toBigDecimal()
                    }
                }
            }
        }
    }
}

val applicationProject = subprojects.filter { it.name.startsWith("app-") }
val snippetsDir by extra { file("build/generated-snippets") }
configure(applicationProject) {
    group = "documentation"

    apply {
        plugin("org.asciidoctor.jvm.convert")
    }

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }

    noArg {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }

    tasks {
        test {
            outputs.dir(snippetsDir)
        }

        asciidoctor {
            inputs.dir(snippetsDir)
            dependsOn(test)
            doFirst {
                delete("src/main/resources/static/docs")
                mkdir("src/main/resources/static/docs")
            }
            doLast {
                copy {
                    from("${buildDir.path}/docs/")
                    into("src/main/resources/static/docs")
                }
            }
        }

        build {
            dependsOn(asciidoctor)
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
        implementation("org.springframework.restdocs:spring-restdocs-mockmvc")

        runtimeOnly("com.h2database:h2")
    }
}
