package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson389478Test : StringSpec({
    "case-1" {
        val actual = Lesson389478().solution(22, 6, 8)
        actual shouldBe 3
    }

    "case-2" {
        val actual = Lesson389478().solution(13, 3, 6)
        actual shouldBe 4
    }
})
