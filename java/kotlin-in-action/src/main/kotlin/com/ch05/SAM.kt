package com.ch05

import com.ch05.java.OnClickListener

fun createAllDoneRunnable() : Runnable {
    return Runnable {
        println("All done!")
    }
}

val listenerOnclick = OnClickListener {
    val text = when (it.id) {
        "first" -> "First button"
        "second" -> "Second button"
        else -> "Unknown button"
    }

    println(text)
}

fun main() {
    createAllDoneRunnable().run()
}
