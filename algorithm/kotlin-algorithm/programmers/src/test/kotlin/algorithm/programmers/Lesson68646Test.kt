package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson68646Test : StringSpec({
    "case-1" {
        val a = intArrayOf(9, -1, -5)
        val actual = Lesson68646().solution(a)
        actual shouldBe 3
    }

    "case-2" {
        val a = intArrayOf(-16, 27, 65, -2, 58, -92, -71, -68, -61, -33)
        val actual = Lesson68646().solution(a)
        actual shouldBe 6
    }
})
