package chapter02.item20

private const val numberFromUser = 99

val percent = when {
    numberFromUser > 100 -> 100
    numberFromUser < 0 -> 0
    else -> numberFromUser
}

val percent2 = numberFromUser.coerceIn(0..100)

fun main() {
    println(percent)
    println(percent2)
}
