package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12977Test : StringSpec({
    "case-1" {
        val nums = intArrayOf(1, 2, 3, 4)
        val actual = Lesson12977().solution(nums)
        actual shouldBe 1
    }

    "case-2" {
        val nums = intArrayOf(1, 2, 7, 6, 4)
        val actual = Lesson12977().solution(nums)
        actual shouldBe 4
    }
})
