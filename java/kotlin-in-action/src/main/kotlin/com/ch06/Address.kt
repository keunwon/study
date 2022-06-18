package com.ch06

class Address(
    val streetAddress: String,
    val zipCode: Int,
    val city: String,
    val country: String,
)

class Company(
    val name: String,
    val address: Address?
)

class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    val country = this.company?.address?.country
    return country ?: "Unknown"
}

fun printShippingBabel(person: Person) {
    val address = person.company?.address
        ?: throw IllegalArgumentException("No Address")

    with (address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}
