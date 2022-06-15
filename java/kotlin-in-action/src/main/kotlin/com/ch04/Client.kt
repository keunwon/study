package com.ch04

class Client(val name: String, val postalCode: Int) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) {
            return false
        }
        return name == other.name && postalCode == other.postalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode

    override fun toString(): String = "Client(name='$name', postalCode=$postalCode)"
}

fun main() {
    val c1 = Client("홍길동", 100)
    val c2 = Client("홍길동", 100)
    println(c1 == c2)

    val clientSet = hashSetOf(c1)
    println(clientSet.contains(Client("홍길동", 100)))
}