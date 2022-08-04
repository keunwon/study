package chapter02.item33

class MyLinkedList<T>(
    val head: T,
    val tail: MyLinkedList<T>?
) {
    companion object {
        fun <T> of(vararg elements: T): MyLinkedList<T>? {
            return null
        }
    }
}

fun <T> myLinkedListOf(vararg elements: T): MyLinkedList<T>? {
    if (elements.isEmpty()) return null
    val head = elements.first()
    val elementsTail = elements.copyOfRange(1, elements.size)
    val tail = myLinkedListOf(*elementsTail)
    return MyLinkedList(head, tail)
}

fun main() {
    val list = myLinkedListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(list)
}
