package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131701Test : StringSpec({
    "case-1" {
        val elements = intArrayOf(7, 9, 1, 1, 4)
        val actual = Lesson131701().solution(elements)
        actual shouldBe 18
    }
})
