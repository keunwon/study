package com.ch04.data

data class Client(val name: String, val postalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (postalCode != other.postalCode) return false

        return true
    }

    override fun hashCode(): Int {
        println("hashCode")
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }
}