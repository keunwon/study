package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson388353Test : StringSpec({
    "case-1" {
        val storage = arrayOf("AZWQY", "CAABX", "BBDDA", "ACACA")
        val requests = arrayOf("A", "BB", "A")

        val actual = Lesson388353().solution(storage, requests)

        actual shouldBe 11
    }

    "case-2" {
        val storage = arrayOf("HAH", "HBH", "HHH", "HAH", "HBH")
        val requests = arrayOf("C", "B", "B", "B", "B", "H")

        val actual = Lesson388353().solution(storage, requests)

        actual shouldBe 4
    }

    "case-3" {
        val storage = arrayOf(
            "BBBB",
            "ACDE",
            "BBBB"
        )
        val requests = arrayOf("CC", "D", "A")

        val actual = Lesson388353().solution(storage, requests)

        actual shouldBe 10
    }

    "case-4" {
        val storage = arrayOf(
            "BBBB",
            "ACDE",
            "BBBB"
        )
        val requests = arrayOf("CC", "A", "D")

        val actual = Lesson388353().solution(storage, requests)

        actual shouldBe 9
    }

    "case-5" {
        val storage = arrayOf(
            "AFFFFF",
            "ADBAAF",
            "ACBBBF",
            "ADFFFF",
        )
        val requests = arrayOf("BB", "A", "C", "A")

        val actual = Lesson388353().solution(storage, requests)

        actual shouldBe 13
    }
})
