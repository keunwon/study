package chapter01.item02

import kotlin.math.sqrt

fun prime() {
    var numbers = (2..100).toList()
    val primes = mutableListOf<Int>()

    while (numbers.isNotEmpty()) {
       val prime = numbers.first()
       primes.add(prime)
       numbers = numbers.filter { it % prime != 0 }
    }
    println(primes)
}

fun prime(size: Int) {
    val primes = BooleanArray(size + 1) { true }
    val lastIndex = sqrt(size + 1.toDouble()).toInt()

    primes[0] = false
    primes[1] = false

    for (i in 2..lastIndex) {
        for (j in i * 2..size step i) {
            primes[j] = false
        }
    }

    val result = primes.mapIndexed { index, b -> index to b }
        .filter { (_, isPrime) -> isPrime }
        .joinToString { (index, _) -> index.toString() }
    println(result)
}

val primes = sequence {
    var numbers = generateSequence(2) { it + 1 }

    while (true) {
        val prime = numbers.first()
        yield(prime)

        numbers = numbers.drop(1)
            .filter { it % prime != 0 }
    }
}

val primes2 = sequence {
    var numbers = generateSequence(2) { it + 1 }

    var prime: Int
    while (true) {
        prime = numbers.first()
        yield(prime)

        numbers = numbers
            .drop(1)
            .filter { it % prime != 0 }
    }
}

fun main() {
    prime()
    prime(100)

    println("===== primes =====")
    println(primes.take(30).toList())
    println("===== primes =====")

    println("===== primes2 =====")
    println(primes2.take(25).toList())
    println("===== primes2 =====")
}
