package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson120861Test : StringSpec({
    "case-1" {
        val actual = Lesson120861().solution(
            keyinput = arrayOf("left", "right", "up", "right", "right"),
            board = intArrayOf(11, 11),
        )
        actual shouldBe intArrayOf(2, 1)
    }

    "case-2" {
        val actual = Lesson120861().solution(
            keyinput = arrayOf("down", "down", "down", "down", "down"),
            board = intArrayOf(7, 9),
        )
        actual shouldBe intArrayOf(0, -4)
    }
})
