package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181851Test : StringSpec({
    "case-1" {
        val rank = intArrayOf(3, 7, 2, 5, 4, 6, 1)
        val attendance = booleanArrayOf(false, true, true, true, true, false, false)

        val actual = Lesson181851().solution(rank, attendance)

        actual shouldBe 20403
    }

    "case-2" {
        val rank = intArrayOf(1, 2, 3)
        val attendance = booleanArrayOf(true, true, true)

        val actual = Lesson181851().solution(rank, attendance)

        actual shouldBe 102
    }

    "case-3" {
        val rank = intArrayOf(6, 1, 5, 2, 3, 4)
        val attendance = booleanArrayOf(true, false, true, false, false, true)

        val actual = Lesson181851().solution(rank, attendance)

        actual shouldBe 50200
    }
})
