package com.ch07.property

interface IWindow {
    fun getWidth(): Int
    fun getHeight(): Int
}

open class TransparentWindow : IWindow {
    override fun getWidth(): Int = 100

    override fun getHeight(): Int = 150
}

class UI(window: IWindow) : IWindow by window {
}

fun main() {
    val window = TransparentWindow()
    val ui = UI(window)

    println("Width: ${ui.getWidth()}, height: ${ui.getHeight()}")
}
