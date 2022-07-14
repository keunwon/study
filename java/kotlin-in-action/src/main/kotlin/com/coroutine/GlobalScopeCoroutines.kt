package com.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun launchInGlobalScope() {
    GlobalScope.launch {
        log("coroutine started.")
    }
}

fun main() {
    log("main() started.")
    launchInGlobalScope()
    log("launchInGlobalScope() executed")

    Thread.sleep(5000L)
    log("main() terminated")
}
