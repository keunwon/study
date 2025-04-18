package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson118667Test : StringSpec({
    "case-1" {
        val queue1 = intArrayOf(3, 2, 7, 2)
        val queue2 = intArrayOf(4, 6, 5, 1)

        val actual = Lesson118667().solution(queue1, queue2)

        actual shouldBe 2
    }

    "case-2" {
        val queue1 = intArrayOf(1, 2, 1, 2)
        val queue2 = intArrayOf(1, 10, 1, 2)

        val actual = Lesson118667().solution(queue1, queue2)

        actual shouldBe 7
    }

    "case-3" {
        val queue1 = intArrayOf(1, 1)
        val queue2 = intArrayOf(1, 5)

        val actual = Lesson118667().solution(queue1, queue2)

        actual shouldBe -1
    }
})
