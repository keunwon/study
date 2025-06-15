package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131702Test : StringSpec({
    "case-1" {
        val actual = Lesson131702().solution(
            arrayOf(
                intArrayOf(0, 3, 3, 0),
                intArrayOf(3, 2, 2, 3),
                intArrayOf(0, 3, 2, 0),
                intArrayOf(0, 3, 3, 3),
            )
        )
        actual shouldBe 3
    }
})
