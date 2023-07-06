package com.keunwon.algorithm.programmers

/**
 * Title: 카드 뭉치
 * Level: 1
 **/
class Lessons159994 {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        val list1 = cards1.toMutableList()
        val list2 = cards2.toMutableList()

        for (w in goal) {
            when (w) {
                list1.getOrNull(0) -> list1.removeFirst()
                list2.getOrNull(0) -> list2.removeFirst()
                else -> return "No"
            }
        }
        return "Yes"
    }
}
