package chapter02.item35

inline fun <T> Iterable<T>.filter2(
    predicate: (T) -> Boolean
): List<T> {
    val list = ArrayList<T>()
    for (element in this) {
        if (predicate(element)) {
            list.add(element)
        }
    }
    return list
}
