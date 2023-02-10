package com.keunwon.auth.common.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun Instant.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(this, ZoneId.systemDefault())

fun Date.toLocalDateTime(): LocalDateTime = this.toInstant().toLocalDateTime()
