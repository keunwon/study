rootProject.name = "kotlin-algorithm"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

include("programmers")
include("baekjoon")
include("leetcode")
