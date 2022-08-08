package chapter02.item40

import java.util.*

class DateTime3(
    private var millis: Long = 0L,
    private var timeZone: TimeZone? = null
) {
    private var asStringCache = ""
    private var changed = false

    override fun equals(other: Any?): Boolean {
        return other is DateTime3 &&
                other.millis == millis &&
                other.timeZone == timeZone
    }

    override fun hashCode(): Int {
        return hashCodeOf(millis, timeZone)
    }
}

fun hashCodeOf(vararg values: Any?) =
    values.fold(0) { acc, value ->
        (acc * 31) + value.hashCode()
    }
