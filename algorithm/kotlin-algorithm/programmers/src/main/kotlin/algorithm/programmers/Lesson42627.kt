package algorithm.programmers

import java.util.*

class Lesson42627 {
    fun solution(jobs: Array<IntArray>): Int {
        jobs.sortBy { it[0] }
        val queue = PriorityQueue<IntArray>(compareBy { it[1] })
        var jobCount = 0
        var index = 0
        var end = 0
        var total = 0

        while (jobCount < jobs.size) {
            while (index < jobs.size && jobs[index][0] <= end) {
                queue.offer(jobs[index])
                ++index
            }

            if (queue.isEmpty()) {
                end = jobs[index][0]
            } else {
                val (start, work) = queue.poll()
                total += end - start + work
                end += work
                jobCount++
            }
        }
        return total / jobs.size
    }
}
