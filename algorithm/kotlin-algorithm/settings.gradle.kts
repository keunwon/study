rootProject.name = "kotlin-algorithm"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/wrapper/libs.versions.toml"))
        }
    }
}
