package com.ch08

data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOW, LINUX, MAC, IOS, ANDROID }

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOW),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/", 12.0, OS.WINDOW),
    SiteVisit("/", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID),
)

val averageWindowsDuration = log
    .filter { it.os == OS.WINDOW }
    .map(SiteVisit::duration)
    .average()

fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit::duration).average()

val averageMobileDuration = log
    .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
    .map(SiteVisit::duration)
    .average()

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()

fun main() {
    println(averageWindowsDuration)
    println(log.averageDurationFor(OS.WINDOW))

    println(averageMobileDuration)
    println(log.averageDurationFor { it.os in setOf(OS.IOS, OS.ANDROID) })
}
