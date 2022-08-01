package chapter02.item21

val map = mapOf(
    "name" to "Marcin",
    "kotlinProgrammer" to true
)

val name by map
val kotlinProgrammer by map
val none by map

fun main() {
    println(name)
    println(kotlinProgrammer)
    println(none)
}
