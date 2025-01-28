rootProject.name = "kotlin-algorithm"
include("src:main:untitled")
findProject(":src:main:untitled")?.name = "untitled"
include("src:main:java")
findProject(":src:main:java")?.name = "java"
