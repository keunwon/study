package algorithm.programmers

import kotlin.collections.iterator
import kotlin.math.max

class Lesson70130 {
    fun solution(a: IntArray): Int {
        val countMap = a.toList().groupingBy { it }.eachCount()
        var answer = 0

        for ((num, count) in countMap) {
            if (count <= answer) continue

            var index = 0
            var tmp = 0

            while (index < a.lastIndex) {
                if ((a[index] != a[index + 1]) && (a[index] == num || a[index + 1] == num)) {
                    ++index
                    ++tmp
                }
                ++index
            }
            answer = max(answer, tmp)
        }
        return answer * 2
    }
}
