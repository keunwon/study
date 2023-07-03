package com.keunwon.algorithm.programmers

/**
 * Title: 시저 암호
 * Level: 1
 **/
class Lessons12926 {
    fun solution(s: String, n: Int): String {
        return s.map {
            if (it.isUpperCase()) 'A' + (it + n - 'A') % 26
            else if (it.isLowerCase()) 'a' + (it + n - 'a') % 26
            else ' '
        }.joinToString("")
    }
}
