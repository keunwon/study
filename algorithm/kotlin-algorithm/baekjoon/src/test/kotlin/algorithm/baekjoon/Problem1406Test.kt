package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem1406Test : StringSpec({
    "case-1" {
        val init = "abcd"
        val commands = arrayOf(
            "P x",
            "L",
            "P y",
        )

        val actual = Problem1406().solution(init, commands)

        actual shouldBe "abcdyx"
    }

    "case-2" {
        val init = "abc"
        val commands = arrayOf(
            "9",
            "L",
            "L",
            "L",
            "L",
            "L",
            "P x",
            "L",
            "B",
            "P y",
        )

        val actual = Problem1406().solution(init, commands)

        actual shouldBe "yxabc"
    }

    "case-3" {
        val init = "dmih"
        val commands = arrayOf(
            "B",
            "B",
            "P x",
            "L",
            "B",
            "B",
            "B",
            "P y",
            "D",
            "D",
            "P z",
        )

        val actual = Problem1406().solution(init, commands)

        actual shouldBe "yxz"
    }
})
