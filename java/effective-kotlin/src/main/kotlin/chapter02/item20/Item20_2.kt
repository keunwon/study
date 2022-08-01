package chapter02.item20

fun Iterable<Int>.product() =
    fold(1) { acc, i -> acc * i }

fun Iterable<Int>.product2() =
    reduce { acc, i -> acc * i }

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    println(list.product())

    val list2 = listOf(1, 2, 3, 4, 5)
    println(list2.product2())

    val emptyList = emptyList<Int>()
    println(emptyList.product())
    println(emptyList.product2())
}
