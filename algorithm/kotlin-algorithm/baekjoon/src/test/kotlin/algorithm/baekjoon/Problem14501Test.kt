package algorithm.baekjoon

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Problem14501Test : StringSpec({
    "case-1" {
        val schedules = arrayOf(
            3 to 10,
            5 to 20,
            1 to 10,
            1 to 20,
            2 to 15,
            4 to 40,
            2 to 200,
        )
        val actual = Problem14501().solution(schedules)
        actual shouldBe 45
    }

    "case-2" {
        val schedules = arrayOf(
            1 to 1,
            1 to 2,
            1 to 3,
            1 to 4,
            1 to 5,
            1 to 6,
            1 to 7,
            1 to 8,
            1 to 9,
            1 to 10,
        )
        val actual = Problem14501().solution(schedules)
        actual shouldBe 55
    }

    "case-3" {
        val schedules = arrayOf(
            5 to 10,
            5 to 9,
            5 to 8,
            5 to 7,
            5 to 6,
            5 to 10,
            5 to 9,
            5 to 8,
            5 to 7,
            5 to 6,
        )
        val actual = Problem14501().solution(schedules)
        actual shouldBe 20
    }
})
