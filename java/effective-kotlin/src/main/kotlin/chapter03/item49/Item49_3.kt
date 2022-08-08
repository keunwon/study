package chapter03.item49

import java.io.File

fun main() {
    File("test.csv").useLines { lines ->
        lines
            .drop(1)
            .mapNotNull { it.split(",").getOrNull(6) }
            .filter { "CANNABITS" in it }
            .count()
            .let { println(it) }
    }
}
