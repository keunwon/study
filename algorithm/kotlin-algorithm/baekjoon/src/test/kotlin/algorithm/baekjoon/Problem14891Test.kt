package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14891Test : StringSpec({
    "case-1" {
        val cogwheels = arrayOf(
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 1),
            intArrayOf(0, 1, 1, 1, 1, 1, 0, 1),
            intArrayOf(1, 1, 0, 0, 1, 1, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 0),
        )
        val rotates = arrayOf(
            intArrayOf(3, -1),
            intArrayOf(1, 1),
        )

        val actual = Problem14891().solution(cogwheels, rotates)

        actual shouldBe 7
    }

    "case-2" {
        val cogWheels = arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
        )
        val rotates = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(2, 1),
            intArrayOf(3, 1),
        )

        val actual = Problem14891().solution(cogWheels, rotates)

        actual shouldBe 15
    }

    "case-3" {
        val cogWheels = arrayOf(
            intArrayOf(1, 0, 0, 0, 1, 0, 1, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 1, 0, 1, 1, 0, 1, 1),
            intArrayOf(0, 0, 1, 1, 1, 1, 0, 1),
        )
        val rotates = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(2, 1),
            intArrayOf(3, 1),
            intArrayOf(4, 1),
            intArrayOf(1, -1),
        )

        val actual = Problem14891().solution(cogWheels, rotates)

        actual shouldBe 6
    }
})
