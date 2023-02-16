plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

val asciidoctorExt: Configuration by configurations.creating
val snippetsDir by extra { file("${buildDir.path}/generated-snippets") }
tasks {
    bootJar { enabled = false }
    jar { enabled = true }

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
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    api("org.springframework.restdocs:spring-restdocs-mockmvc")
    api("org.junit.jupiter:junit-jupiter-api")
    api("javax.servlet:javax.servlet-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
