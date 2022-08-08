package chapter02.item39

sealed class ValueMatcher2<T> {
    abstract fun match(value: T): Boolean
}

class Equal<T>(private val value: T) : ValueMatcher2<T>() {
    override fun match(value: T): Boolean = value == this.value
}

class NotEqual<T>(private val value: T) : ValueMatcher2<T>() {
    override fun match(value: T): Boolean = value != this.value
}

class EmptyList<T> : ValueMatcher2<T>() {
    override fun match(value: T): Boolean =
        value is List<*> && value.isEmpty()
}

class NotEmptyList<T> : ValueMatcher2<T>() {
    override fun match(value: T): Boolean =
        value is List<*> && value.isNotEmpty()
}

fun main() {
    println(Equal(3).match(3))
    println(Equal(3).match(4))

    println(NotEqual(3).match(3))
    println(NotEqual(3).match(4))

    println(EmptyList<List<String>>().match(emptyList()))
    println(EmptyList<List<String>>().match(listOf("a", "b", "c")))

    println(NotEmptyList<List<String>>().match(emptyList()))
    println(NotEmptyList<List<String>>().match(listOf("a", "b", "c", "d")))
}
