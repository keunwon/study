package com.keunwon.auth.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface LogSupport {
    val log: Logger
        get() = LoggerFactory.getLogger(this::class.java)
}
