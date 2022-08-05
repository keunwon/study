package chapter02.item33

inline fun <T> List2(
    size: Int,
    init: (index: Int) -> T
): List<T> = MutableList2(size, init)

inline fun <T> MutableList2(
    size: Int,
    init: (index: Int) -> T
): MutableList<T> {
    val list = ArrayList<T>(size)
    repeat(size) { index -> list.add(init(index)) }
    return list
}

fun main() {
    val list = List2(26) { 'a' + it }
    println(list.joinToString())
}
