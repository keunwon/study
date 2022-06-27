package com.ch07

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}
