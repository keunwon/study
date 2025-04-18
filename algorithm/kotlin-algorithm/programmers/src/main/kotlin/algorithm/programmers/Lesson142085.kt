package algorithm.programmers

import java.util.*

class Lesson142085 {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val queue = PriorityQueue<Int>(compareByDescending { it })
        var hp = n
        var pass = k

        for ((i, e) in enemy.withIndex()) {
            queue.offer(e)
            hp -= e

            if (hp < 0) {
                if (pass == 0) return i
                --pass
                hp += queue.poll()
            }
        }
        return enemy.size
    }
}
