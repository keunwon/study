package com.ch06

import com.ch06.java.Person

fun strLen(s: String) = s.length

fun strLenSafe(s: String?) = s?.length ?: 0

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase()
    println(allCaps)
}

fun ignoreNulls(s: String?) {
    val aNotNull: String = s!!
    println(aNotNull.length)
}

fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}

fun String?.isNullOrBlank(): Boolean =
    this == null || this.isBlank()

/*
 // null 타입으로 받을 수 있음
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}
*/

fun <T: Any> printHashCode(t: T) {
    println(t.hashCode())
}

fun yellAt(person: Person) {
    println((person.name ?: "Anyone").uppercase() + "!!!")
}
