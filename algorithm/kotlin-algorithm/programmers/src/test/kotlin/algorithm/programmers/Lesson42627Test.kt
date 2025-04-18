package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42627Test : StringSpec({
    "case-1" {
        val jobs = arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 9),
            intArrayOf(2, 6)
        )
        val actual = Lesson42627().solution(jobs)
        actual shouldBe 9
    }
})
