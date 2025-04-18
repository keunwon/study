package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson150369Test : StringSpec({
    "case-1" {
        val cap = 4
        val n = 5
        val deliveries = intArrayOf(1, 0, 3, 1, 2)
        val pickups = intArrayOf(0, 3, 0, 4, 0)

        val actual = Lesson150369().solution(cap, n, deliveries, pickups)

        actual shouldBe 16
    }

    "case-2" {
        val cap = 2
        val n = 7
        val deliveries = intArrayOf(1, 0, 2, 0, 1, 0, 2)
        val pickups = intArrayOf(0, 2, 0, 1, 0, 2, 0)

        val actual = Lesson150369().solution(cap, n, deliveries, pickups)

        actual shouldBe 30
    }
})
