package com.keunwon.algorithm.againresolve

class ALessons154538 {
    fun solution(x: Int, y: Int, n: Int): Int {
        val numbers = mutableSetOf<Int>().apply { add(x) }
        var count = 0

        while (numbers.isNotEmpty()) {
            if (numbers.contains(y)) return count

            val calcNumbers = numbers.flatMap { num ->
                intArrayOf(num + n, num * 2, num * 3).takeWhile { it <= y }
            }
            numbers.clear()
            numbers.addAll(calcNumbers)
            ++count
        }
        return -1
    }
}

fun main() {
    ALessons154538().solution(10, 40, 5).let(::println)
    ALessons154538().solution(10, 40, 30).let(::println)
    ALessons154538().solution(2, 5, 4).let(::println)
}
