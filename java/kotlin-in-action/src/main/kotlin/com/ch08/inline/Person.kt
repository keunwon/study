package com.ch08.inline

data class Person(val name: String, val age: Int)

fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found")
            return
        }
    }
    println("Alice is not found")
}

fun lookForAlice2(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return
        }
        println("Alice is not found")
    }
}

fun lookForAlice3(people: List<Person>) {
    people.forEach label@{
        if (it.name == "Alice") return@label
    }
    println("Alice is not found")
}

fun lookForAlice4(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") return@forEach
    }
    println("Alice is not found")
}

fun lookForAlice5(people: List<Person>) {
    people.forEach(fun (person) {
        if (person.name == "Alice") return
        println("${person.name} is not Alice")
    })
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    println(people.filter { it.age < 30 })
    lookForAlice(people)
    lookForAlice2(people)
    lookForAlice3(people)
    lookForAlice4(people)
    lookForAlice5(people)

    println(people.filter(fun (person): Boolean { return person.age < 30 }))
    println(people.filter(fun (person) = person.age < 30))
}
