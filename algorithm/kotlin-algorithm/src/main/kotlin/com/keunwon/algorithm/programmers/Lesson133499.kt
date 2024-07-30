package com.keunwon.algorithm.programmers

class Lesson133499 {
    fun solution(babbling: Array<String>): Int {
        var result = 0

        for (i in babbling.indices) {
            babbling[i] = babbling[i].replace("ayaaya", "1")
            babbling[i] = babbling[i].replace("yeye", "1")
            babbling[i] = babbling[i].replace("woowoo", "1")
            babbling[i] = babbling[i].replace("mama", "1")

            babbling[i] = babbling[i].replace("aya", "")
            babbling[i] = babbling[i].replace("ye", "")
            babbling[i] = babbling[i].replace("woo", "")
            babbling[i] = babbling[i].replace("ma", "")

            if (babbling[i].isBlank()) ++result
        }
        return result
    }
}
