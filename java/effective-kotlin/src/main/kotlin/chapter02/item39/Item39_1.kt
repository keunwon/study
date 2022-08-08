package chapter02.item39

class ValueMatcher<T> private constructor(
    private val value: T? = null,
    private val matcher: Matcher
) {

    fun match(value: T?) = when (matcher) {
        Matcher.EQUAL -> value == this.value
        Matcher.NOT_EQUAL -> value != this.value
        Matcher.LIST_EMPTY -> value is List<*> && value.isEmpty()
        Matcher.LIST_NOT_EMPTY -> value is List<*> && value.isNotEmpty()
    }

    enum class Matcher {
        EQUAL,
        NOT_EQUAL,
        LIST_EMPTY,
        LIST_NOT_EMPTY
    }

    companion object {
        fun <T> equal(value: T) =
            ValueMatcher(value = value, matcher = Matcher.EQUAL)

        fun <T> notEqual(value: T) =
            ValueMatcher(value = value, matcher = Matcher.NOT_EQUAL)

        fun emptyList() =
            ValueMatcher(value = null, matcher = Matcher.LIST_EMPTY)

        fun <T> notEmptyList() =
            ValueMatcher<T>(matcher = Matcher.LIST_NOT_EMPTY)
    }
}
