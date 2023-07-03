package com.keunwon.algorithm.programmers

/**
 * Title: 예상 대진표
 * Level: 2
 **/
class Lessons12985 {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0
        var aa = a
        var bb = b

        while (aa != bb) {
            aa = aa / 2 + aa % 2
            bb = bb / 2 + bb % 2
            ++answer
        }
        return answer
    }
}
