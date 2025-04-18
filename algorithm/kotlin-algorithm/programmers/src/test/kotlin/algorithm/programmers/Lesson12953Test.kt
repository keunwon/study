package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12953Test : StringSpec({
    "case-1" {
        val arr = intArrayOf(2, 6, 8, 14)
        val actual = Lesson12953().solution(arr)
        actual shouldBe 168
    }

    "case-2" {
        val arr = intArrayOf(1, 2, 3)
        val actual = Lesson12953().solution(arr)
        actual shouldBe 6
    }
})
