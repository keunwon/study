package chapter03.item47

typealias Seconds2 = Int
typealias Millis2 = Int

fun getTime(): Millis2 = 10
fun setUpTimer(time: Seconds2) {}

fun main() {
    val second: Seconds2 = 10
    val millis: Millis2 = second

    setUpTimer(getTime())
}
