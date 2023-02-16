package com.github.keunwon.core

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

fun Date.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(toInstant(), ZoneId.systemDefault())
}

fun LocalDateTime.toDate(): Date = Date.from(toInstant(ZoneOffset.UTC))
