package com.keunwon.algorithm.programmers

/**
 * Title: 둘만의 암호
 * Level: 1
 **/
class Lessons155652 {
    fun solution(s: String, skip: String, index: Int): String {
        val alphabets = ('a'..'z').filterNot { it in skip }
        return s.map { w ->
            val targetIndex = (alphabets.indexOf(w) + index) % alphabets.size
            alphabets[targetIndex]
        }.joinToString("")
    }
}
