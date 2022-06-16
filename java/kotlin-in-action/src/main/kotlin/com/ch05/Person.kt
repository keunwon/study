package com.ch05

data class Person(val name: String, val age: Int)

fun Person.isAdult() = age >= 21

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null

    for (person in people) {
        if (maxAge < person.age) {
            maxAge = person.age
            theOldest = person
        }
    }

    println(theOldest)
}

fun findTheOldestByLambda(people: List<Person>) =
    people.maxByOrNull { it.age }

val sum = { x: Int, y: Int -> x + y}

val getAge = { person: Person -> person.age }