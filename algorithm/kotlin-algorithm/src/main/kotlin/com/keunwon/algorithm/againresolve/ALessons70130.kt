package com.keunwon.algorithm.againresolve

class ALessons70130 {
    fun solution(a: IntArray): Int {
        val countMap = a.toList().groupingBy { it }.eachCount()
        var answer = 0

        for ((num, count) in countMap) {
            if (count < answer) continue

            var i = 0
            var n = 0
            while (i < a.lastIndex) {
                if (a[i] != a[i + 1] && num in intArrayOf(a[i], a[i + 1])) {
                    i++
                    n++
                }
                i++
            }
            answer = answer.coerceAtLeast(n)
        }
        return answer * 2
    }
}

fun main() {
    arrayOf(
        intArrayOf(0),
        intArrayOf(5, 2, 3, 3, 5, 3),
        intArrayOf(0, 3, 3, 0, 7, 2, 0, 2, 2, 0),
    ).forEach { a ->
        ALessons70130().solution(a).also { println(it) }
    }
}
