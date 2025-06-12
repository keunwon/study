package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42862Test : StringSpec({
    "case-1" {
        val actual = Lesson42862().solution(
            n = 5,
            lost = intArrayOf(2, 4),
            reserve = intArrayOf(1, 3, 5),
        )
        actual shouldBe 5
    }

    "case-2" {
        val actual = Lesson42862().solution(
            n = 5,
            lost = intArrayOf(2, 4),
            reserve = intArrayOf(3),
        )
        actual shouldBe 4
    }

    "case-3" {
        val actual = Lesson42862().solution(
            n = 3,
            lost = intArrayOf(3),
            reserve = intArrayOf(1),
        )
        actual shouldBe 2
    }
})
