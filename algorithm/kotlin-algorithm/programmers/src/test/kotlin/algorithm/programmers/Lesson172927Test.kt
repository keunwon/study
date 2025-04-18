package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson172927Test : StringSpec({
    "case-1" {
        val picks = intArrayOf(1, 3, 2)
        val minerals = arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")

        val actual = Lesson172927().solution(picks, minerals)

        actual shouldBe 12
    }

    "case-2" {
        val picks = intArrayOf(0, 1, 1)
        val minerals = arrayOf(
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "iron",
            "iron",
            "iron",
            "iron",
            "iron",
            "diamond"
        )

        val actual = Lesson172927().solution(picks, minerals)

        actual shouldBe 50
    }
})
