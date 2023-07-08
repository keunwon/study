package com.keunwon.algorithm.programmers

/**
 * Title: 햄버거 만들기
 * Level: 1
 **/
class Lessons133502 {
    fun solution(ingredient: IntArray): Int {
        var answer = 0
        val list = mutableListOf<Int>()

        for (n in ingredient) {
            list.add(n)
            if (list.size < 4) continue

            val tmp = list.takeLast(4).joinToString("")
            if (tmp == "1231") {
                answer++
                repeat(4) { list.removeLast() }
            }
        }
        return answer
    }
}
