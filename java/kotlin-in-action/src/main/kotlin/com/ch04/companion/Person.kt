package com.ch04.companion

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {

    companion object : JSONFactory<Person> {
        private val objectMapper = ObjectMapper().registerKotlinModule()

        override fun fromJSON(jsonText: String): Person {
            return objectMapper.readValue(jsonText, Person::class.java)
        }
    }
}