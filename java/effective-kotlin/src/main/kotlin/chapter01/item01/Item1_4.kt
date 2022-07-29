package chapter01.item01

fun main() {
    val list = listOf(1, 2, 3)

    if (list is MutableList) {
        println("pass")
        list.add(4)
    }
}
