package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: A와 B 2
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/12919">A와 B 2 (골드-5)</a>
 **/
class Problem12919 {
    fun solution(s: String, t: String): Int {
        val q = LinkedList<String>().apply { offer(t) }

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (cur.isBlank()) break
            if (cur == s) return 1

            if (cur.last() == 'A') q.offer(cur.dropLast(1))
            if (cur.first() == 'B') q.offer(cur.drop(1).reversed())
        }
        return 0
    }
}

fun main() {
    val s = readln()
    val t = readln()

    Problem12919().solution(s, t).also { println(it) }
}
