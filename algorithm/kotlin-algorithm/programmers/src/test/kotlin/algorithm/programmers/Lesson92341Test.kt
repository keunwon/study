package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson92341Test : StringSpec({
    "case-1" {
        val fees = intArrayOf(180, 5000, 10, 600)
        val records = arrayOf(
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT"
        )

        val actual = Lesson92341().solution(fees, records)

        actual shouldBe intArrayOf(14600, 34400, 5000)
    }

    "case-2 " {
        val fees = intArrayOf(120, 0, 60, 591)
        val records = arrayOf("16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN")

        val actual = Lesson92341().solution(fees, records)

        actual shouldBe intArrayOf(0, 591)
    }

    "case-3" {
        val fees = intArrayOf(1, 461, 1, 10)
        val records = arrayOf("00:00 1234 IN")

        val actual = Lesson92341().solution(fees, records)

        actual shouldBe intArrayOf(14841)
    }
})
