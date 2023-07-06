package com.keunwon.algorithm.programmers

/**
 * Title: 옹알이 (2)
 * Level: 1
 **/
class Lessons133499 {
    fun solution(babbling: Array<String>): Int {
        val words = setOf("aya", "ye", "woo", "ma")
        return babbling.count { s ->
            var tmp = s
            for (word in words) {
                if (tmp.contains("$word$word")) continue
                tmp = tmp.replace(word, " ")
            }
            tmp.isBlank()
        }
    }
}
