package chapter03.item45

fun <T: Comparable<T>> Iterable<T>.countMax(): Int =
    count { it == this.maxOrNull() }

fun <T: Comparable<T>> Iterable<T>.countMax2(): Int {
    val max = this.maxOrNull()
    return count { it == max }
}

fun main() {
    val list = listOf(1, 2, 3, 4, 4)
    println(list.countMax())
    println(list.countMax2())
}
