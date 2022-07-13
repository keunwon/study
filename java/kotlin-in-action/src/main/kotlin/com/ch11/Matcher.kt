package com.ch11

interface Matcher<T> {
    fun test(value: T)
}

class StartWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix))
            throw AssertionError("String $value does not start with $prefix")
    }
}

object Start
infix fun String.should(x: String): StartWrapper = StartWrapper(this)

class StartWrapper(val value: String) {
    infix fun with(prefix: String) {
        if (!value.startsWith(prefix)) {
            throw AssertionError("String does not start with $prefix: $value")
        }
    }
}
