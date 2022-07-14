package com.supplement.v1

enum class DAYSOFWEEK { MON, TUE, WED, THR, FRI, SAT, SUN }

inline fun <reified T : Enum<T>> mkString(): String =
    buildString {
        for (v in enumValues<T>()) {
            append(v)
            append(", ")
        }
    }

inline fun <reified T : Enum<T>> enumFrom(name: String): T {
    return enumValueOf(name)
}
