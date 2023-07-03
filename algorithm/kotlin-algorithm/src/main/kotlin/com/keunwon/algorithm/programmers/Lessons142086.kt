package com.keunwon.algorithm.programmers


/**
 * Title: 가장 가까운 같은 글자
 * Level: 1
 **/
class Lessons142086 {
    fun solution(s: String): IntArray {
        val alphabet = mutableMapOf<Char, Int>()
        val answer = IntArray(s.length)

        for ((index, c) in s.withIndex()) {
            if (alphabet.containsKey(c)) answer[index] = index - alphabet.getValue(c)
            else answer[index] = -1
            alphabet[c] = index
        }
        return answer
    }
}
