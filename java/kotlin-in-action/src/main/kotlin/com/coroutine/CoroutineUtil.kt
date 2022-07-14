package com.coroutine

import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun now() = ZonedDateTime.now().toLocalDateTime().truncatedTo(ChronoUnit.MILLIS)

fun log(msg: String) = println("${now()}: ${Thread.currentThread()}: $msg")
