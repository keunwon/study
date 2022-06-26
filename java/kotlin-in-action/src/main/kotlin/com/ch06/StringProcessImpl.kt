package com.ch06

import com.ch06.java.StringProcessor

class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        value?.let { println(it) }
    }
}
