package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson64063Test : StringSpec({
    "case-1" {
        val k = 10L
        val room_number = longArrayOf(1, 3, 4, 1, 3, 1)

        val actual = Lesson64063().solution(k, room_number)

        actual shouldBe longArrayOf(1, 3, 4, 2, 5, 6)
    }
})
