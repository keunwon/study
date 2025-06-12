package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181921Test : StringSpec({
    "case-1" {
        val actual = Lesson181921().solution(
            l = 5,
            r = 555,
        )
        actual shouldBe intArrayOf(5, 50, 55, 500, 505, 550, 555)
    }

    "case-2" {
        val actual = Lesson181921().solution(
            l = 10,
            r = 20,
        )
        actual shouldBe intArrayOf(-1)
    }

    "case-3" {
        val actual = Lesson181921().solution(
            l = 4,
            r = 556,
        )
        actual shouldBe intArrayOf(5, 50, 55, 500, 505, 550, 555)
    }

    "case-4" {
        val actual = Lesson181921().solution(
            l = 7,
            r = 55,
        )
        actual shouldBe intArrayOf(50, 55)
    }
})
