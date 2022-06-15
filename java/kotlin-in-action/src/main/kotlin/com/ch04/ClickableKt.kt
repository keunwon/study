package com.ch04

interface Clickable {

    fun click()

    fun showOff() = println("I'm clickable!")
}

interface Focusable {

    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {

    override fun click() = println("I was clicked")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

open class RichButton : Clickable {

    fun disable() {}

    open fun animate() {}

    override fun click() {}
}

abstract class Animated {

    abstract fun animate()

    open fun stopAnimating() {}

    fun animateTwice() {}
}

class AnimateImpl : Animated() {

    override fun animate() = println("animate")

    override fun stopAnimating() = println("stopAnimating")
}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()

    val animate = AnimateImpl()
    animate.animate()
    animate.stopAnimating()
}