package com.spring.sre.chapter02.config

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface Log4j2Support {
    val log: Logger
        get() = LogManager.getLogger(this::class)
}

class Log4j2Delegate<in R : Any> : ReadOnlyProperty<R, Logger> {
    override fun getValue(thisRef: R, property: KProperty<*>): Logger = LogManager.getLogger(thisRef::class)
}
