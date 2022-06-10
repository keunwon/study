package com.ch03

fun toRegexArray(text: String) = text.split("[.\\-]".toRegex())

fun toRegexArray2(text: String) = text.split(".", "-")

fun parsePath(path: String): Map<String, String> {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    return mapOf(
        "directory" to directory,
        "fileName" to fileName,
        "extension" to extension
    )
}

fun parseRegexPath(path: String): Map<String, String> {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path) ?: return emptyMap()

    val (directory, filename, extension) = matchResult.destructured
    return mapOf(
        "directory" to directory,
        "fileName" to filename,
        "extension" to extension
    )
}