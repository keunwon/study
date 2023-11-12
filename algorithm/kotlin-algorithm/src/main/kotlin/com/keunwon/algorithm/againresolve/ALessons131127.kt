package com.keunwon.algorithm.againresolve

class ALessons131127 {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val target = want.zip(number.toList())
        var answer = 0

        for (i in 0 until discount.size - 9) {
            val buckets = discount.slice(i until i + 10)
                .groupingBy { it }
                .eachCount()
            val matches = target.all { buckets[it.first] == it.second }
            if (matches) ++answer
        }
        return answer
    }
}

fun main() {
    ALessons131127().solution(
        arrayOf("banana", "apple", "rice", "pork", "pot"),
        intArrayOf(3, 2, 2, 2, 1),
        arrayOf(
            "chicken",
            "apple",
            "apple",
            "banana",
            "rice",
            "apple",
            "pork",
            "banana",
            "pork",
            "rice",
            "pot",
            "banana",
            "apple",
            "banana"
        )
    ).also(::println)
}
