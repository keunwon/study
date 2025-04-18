package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson120876Test : StringSpec({
    "case-1" {
        val lines = arrayOf(intArrayOf(0, 1), intArrayOf(2, 5), intArrayOf(3, 9))
        val actual = Lesson120876().solution(lines)
        actual shouldBe 2
    }

    "case-2" {
        val lines = arrayOf(intArrayOf(1, 1), intArrayOf(1, 3), intArrayOf(3, 9))
        val actual = Lesson120876().solution(lines)
        actual shouldBe 0
    }

    "case-3" {
        val liens = arrayOf(intArrayOf(0, 5), intArrayOf(3, 9), intArrayOf(1, 10))
        val actual = Lesson120876().solution(liens)
        actual shouldBe 8
    }
})
