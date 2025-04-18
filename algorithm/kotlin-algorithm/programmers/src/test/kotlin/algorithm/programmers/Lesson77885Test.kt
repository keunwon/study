package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson77885Test : StringSpec({
    "case-1" {
        val numbers = longArrayOf(2, 7, 9)
        val actual = Lesson77885().solution(numbers)
        actual shouldBe longArrayOf(3, 11, 10)
    }
})
