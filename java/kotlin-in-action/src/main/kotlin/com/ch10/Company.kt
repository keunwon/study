package com.ch10

import com.ch10.jkid.DeserializeInterface
import kotlin.reflect.full.memberProperties

interface Company {
    val name: String
}

data class CompanyImpl(override val name: String) : Company

data class Person(
    val name: String,
    @DeserializeInterface(CompanyImpl::class) val company: Company
)

data class Person2(val name: String, val age: Int)

fun foo(x: Int) = println(x)

fun sum(x: Int, y: Int) = x + y


internal var counter = 0
internal val counterProperty = ::counter


fun main() {
    val person = Person2("Alice", 29)
    val kClass = person.javaClass.kotlin
    println(kClass)
    kClass.memberProperties.forEach { println(it.name) }

    println("===== KFunction =====")
    val kFunction = ::foo
    kFunction.call(11)
    kFunction.invoke(22)
    kFunction(33)

    val sumKFunction = ::sum
    println(sumKFunction.invoke(1, 2) + sumKFunction(3, 4) == 10)

    println("===== KProperty =====")
    counterProperty.setter.call(21)
    println(counterProperty.get())

    val memberProperty = Person2::age
    println(memberProperty.get(person))


}
