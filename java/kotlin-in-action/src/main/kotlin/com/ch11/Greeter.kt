package com.ch11

class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name")
    }
}
