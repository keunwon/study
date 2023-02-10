plugins {
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("java-test-fixtures")
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    kotlin("kapt") version "1.7.22"
}

group = "com.keunwon"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


extra.apply {
    set("jwtVersion", "0.11.5")
    set("mockkVersion", "1.13.3")
    set("restAssuredVersion", "5.3.0")
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
        plugin("org.asciidoctor.jvm.convert")
        plugin("java-test-fixtures")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.kapt")
    }


    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    // spring rest-doc
    val asciidoctorExt: Configuration by configurations.creating
    val snippetsDir by extra { file("build/generated-snippets") }
    dependencies {
        asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
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
            testLogging {
                showCauses = true
            }
            outputs.dir(snippetsDir)
        }

        asciidoctor {
            inputs.dir(snippetsDir)
            configurations(asciidoctorExt.name)
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
}
