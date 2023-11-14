package com.keunwon.algorithm.againresolve

class ALessons42883 {
    fun solution(number: String, k: Int): String {
        val answer = StringBuilder()
        var startIndex = 0

        for (i in 0 until number.length - k) {
            var max = 0

            for (j in startIndex..i + k) {
                val num = number[j] - '0'
                if (max < num) {
                    max = num
                    startIndex = j
                }
            }
            answer.append(max)
            ++startIndex
        }
        return answer.toString()
    }
}

fun main() {
    ALessons42883().solution("1924", 2).also(::println)
    ALessons42883().solution("1231234", 3).also(::println)
    ALessons42883().solution("4177252841", 4).also(::println)
}
