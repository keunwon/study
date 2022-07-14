package com.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun runBlockingExample() {
    runBlocking {
        launch {
            log("GlobalScope.launch started.")
        }
    }
}

fun yieldExample() {
    runBlocking {
        launch {
            log("1")
            yield()
            log("3")
            yield()
            log("5")
        }
        log("after first launch")
        launch {
            log("2")
            delay(1000L)
            log("4")
            delay(1000L)
            log("6")
        }
        log("after second launch")
    }
}

fun main() {
    log("main() started.")
    //runBlockingExample()
    yieldExample()
    log("launchInGlobalScope() executed")

    log("main() terminated")
}
