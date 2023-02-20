package com.github.keunwon.core.generic.time

import com.github.keunwon.core.isAfterOrEquals
import com.github.keunwon.core.isBeforeOrEquals
import java.time.LocalDateTime

class DateTimePeriod private constructor(
    private val from: LocalDateTime,
    private val to: LocalDateTime,
) {
    fun contains(dateTime: LocalDateTime): Boolean {
        return dateTime.isAfterOrEquals(from) && dateTime.isBeforeOrEquals(to)
    }

    companion object {
        fun between(from: LocalDateTime, to: LocalDateTime): DateTimePeriod = DateTimePeriod(from, to)
    }
}

