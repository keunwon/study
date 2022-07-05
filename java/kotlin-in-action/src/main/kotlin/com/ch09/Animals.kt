package com.ch09

open class Animal {
    fun feed() = println("feed")
}

class Herd<out T : Animal>(private val animals: List<T>) {
    constructor(vararg animals: T) : this(animals.asList())

    val size: Int
        get() = animals.size

    operator fun get(i: Int): T {
        return animals[i]
    }
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Cat : Animal() {
    fun cleanLitter() = println("clean")
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        feedAll(cats)
    }
}

fun main() {
    val animals = Herd(Animal(), Animal(), Animal())
    feedAll(animals)

    val cats = Herd(Cat(), Cat(), Cat())
    takeCareOfCats(cats)
}
