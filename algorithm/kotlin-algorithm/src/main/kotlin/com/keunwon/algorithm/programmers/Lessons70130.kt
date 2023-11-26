package com.keunwon.algorithm.programmers

/**
 * Title: 스타 수열
 * Level: 3
 **/
// todo
class Lessons70130 {
    fun solution(a: IntArray): Int {
        val countMap = a.toList().groupingBy { it }.eachCount()
        var answer = 0

        for ((key, count) in countMap) {
            if (count <= answer) continue

            var index = 0
            var tmp = 0
            while (index < a.lastIndex) {
                if (a[index] != a[index + 1] && (a[index] == key || a[index + 1] == key)) {
                    ++index
                    ++tmp
                }
                ++index
            }
            answer = answer.coerceAtLeast(tmp)
        }
        return answer * 2
    }
}
