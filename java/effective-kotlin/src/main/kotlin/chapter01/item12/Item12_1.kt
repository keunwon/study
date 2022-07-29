package chapter01.item12

/*private operator fun Int.times(operation: () -> Unit): () -> Unit =
    { repeat(this) { operation() } }*/

private operator fun Int.times(operation: () -> Unit) {
    repeat(this) { operation() }
}

infix fun Int.timesRepeated(operation: () -> Unit): () -> Unit = {
    repeat(this) { operation() }
}

fun main() {
    3 * { println("Hello") }
    println("-----")

    val tripledHello = 3 timesRepeated { println("Hello") }
    tripledHello()
    println("-----")

    repeat(3) { println("Hello") }
}
