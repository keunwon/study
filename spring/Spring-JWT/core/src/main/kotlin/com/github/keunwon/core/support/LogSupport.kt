package com.github.keunwon.core.support

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

interface LogSupport {
    val log: Logger
        get() = LogManager.getLogger(this::class.java)
}
