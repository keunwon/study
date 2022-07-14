@file:OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.coroutine

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun sumAll() {
    runBlocking {
        val d1 = async { delay(1000L); 1 }
        log("after async(d1)")
        val d2 = async { delay(2000L); 2 }
        log("after async(d2)")
        val d3 = async { delay(3000L); 3 }
        log("after async(d3)")

        log("1+2+3 = ${d1.await() + d2.await() + d3.await()}")
        log("after await all & add")
    }
}

fun dispatch() {
    runBlocking {
        launch {
            println("main runBlocking: I'm working in thread [${Thread.currentThread().name}]")
        }

        launch(context = Dispatchers.Unconfined) {
            println("Unconfined : I'm working in thread [${Thread.currentThread().name}]")
        }

        launch(Dispatchers.Default) {
            println("Default : I'm working in thread [${Thread.currentThread().name}]")
            delay(5000L)
        }

        launch(newSingleThreadContext("MyOwnThread")) {
            println("newSingleThreadContext: I'm working in thread [${Thread.currentThread().name}]")
        }
    }
}

suspend fun yieldThreeTimes() {
    log("1")
    delay(1000L)
    yield()
    log("2")
    delay(1000L)
    yield()
    log("3")
    delay(1000L)
    yield()
    log("4")
}

fun suspendExample() {
    GlobalScope.launch { yieldThreeTimes() }
}

fun main() {
    /*println("----- sumAll -----")
    sumAll()

    println("----- dispatch -----")
    dispatch()*/

    suspendExample()
    Thread.sleep(10000L)
}
