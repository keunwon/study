package chapter03.item49

fun main() {
    generateSequence(1) { it + 1 }
        .map { it * 2 }
        .take(10)
        .forEach { print("$it, ") }

    println()

    val fibonacci = sequence {
        yield(1)
        var current = 1
        var prev = 1
        while (true) {
            yield(current)
            val temp = prev
            prev = current
            current += temp
        }
    }

    println(fibonacci.take(10).toList())
}
