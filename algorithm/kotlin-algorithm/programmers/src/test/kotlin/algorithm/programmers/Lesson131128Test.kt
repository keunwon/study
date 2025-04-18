package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson131128Test : StringSpec({
    "case-1" {
        val x = "100"
        val y = "2345"

        val actual = Lesson131128().solution(x, y)

        actual shouldBe "-1"
    }

    "case-2" {
        val x = "100"
        val y = "203045"

        val actual = Lesson131128().solution(x, y)

        actual shouldBe "0"
    }

    "case-3" {
        val x = "100"
        val y = "123450"

        val actual = Lesson131128().solution(x, y)

        actual shouldBe "10"
    }

    "case-4" {
        val x = "12321"
        val y = "42531"

        val actual = Lesson131128().solution(x, y)

        actual shouldBe "321"
    }

    "case-5" {
        val x = "5525"
        val y = "1255"

        val actual = Lesson131128().solution(x, y)

        actual shouldBe "552"
    }
})
