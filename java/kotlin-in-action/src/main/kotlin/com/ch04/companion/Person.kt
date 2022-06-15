package com.ch04.companion

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {

    companion object : JSONFactory<Person> {
        override fun fromJSON(jsonText: String): Person {
            return jacksonObjectMapper().readValue(jsonText, Person::class.java)
        }
    }
}

fun Person.Companion.fromJson2(jsonText: String): Person =
    jacksonObjectMapper().readValue(jsonText, Person::class.java)