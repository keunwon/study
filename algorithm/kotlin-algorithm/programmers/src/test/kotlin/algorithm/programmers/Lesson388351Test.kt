package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson388351Test : StringSpec({
    "case-1" {
        val schedules = intArrayOf(1059, 700, 800, 1100)
        val timelogs = arrayOf(
            intArrayOf(710, 2359, 1050, 700, 650, 631, 659),
            intArrayOf(800, 801, 805, 800, 759, 810, 809),
            intArrayOf(1105, 1001, 1002, 600, 1059, 1001, 1100),
        )
        val startday = 7

        val actual = Lesson388351().solution(schedules, timelogs, startday)

        actual shouldBe 3
    }

    "case-2" {
        val schedules = intArrayOf(730, 855, 700, 720)
        val timelogs = arrayOf(
            intArrayOf(710, 700, 650, 735, 700, 931, 912),
            intArrayOf(908, 901, 805, 815, 800, 831, 835),
            intArrayOf(705, 701, 702, 705, 710, 710, 711),
            intArrayOf(707, 731, 859, 913, 934, 931, 905),
        )
        val startday = 1

        val actual = Lesson388351().solution(schedules, timelogs, startday)

        actual shouldBe 2
    }
})
