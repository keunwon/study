package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson161988Test : StringSpec({
    "case-1" {
        val sequence = intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)
        val actual = Lesson161988().solution(sequence)
        actual shouldBe 10
    }
})
