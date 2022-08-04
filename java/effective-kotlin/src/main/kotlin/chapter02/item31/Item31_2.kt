package chapter02.item31

fun <T> Collection<T>.powerset(): Set<Set<T>> =
    if (isEmpty()) setOf(emptySet())
    else take(size - 1)
        .powerset()
        .let { it + it.map { it + last() } }

/**
 * 리시버 집합의 모든 부분 집합을 리턴합니다.
 * (자기 자신과 빈 집합을 포함합니다.)
 */
fun <T> Collection<T>.powerset2(): Set<Set<T>> =
    powerset(this, setOf(setOf()))

private tailrec fun <T> powerset(
    left: Collection<T>,
    acc: Set<Set<T>>
): Set<Set<T>> = when {
    left.isEmpty() -> acc
    else -> {
        val head = left.first()
        val tail = left.drop(1)
        powerset(tail, acc + acc.map { it + head })
    }
}

fun main() {
    val list = (1..5).toList()
    println(list.powerset2())
}
