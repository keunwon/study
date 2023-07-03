package com.keunwon.algorithm.programmers

/**
 * Title: 숫자 문자열과 영단어
 * Level: 1
 **/
class Lessons81301 {
    fun solution(s: String): Int {
        val alphabet = mapOf(
            0 to "zero",
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine",
        )
        var answer = s
        alphabet.forEach { (key, value) ->
            answer = answer.replace(value, "$key")
        }
        return answer.toInt()
    }
}
