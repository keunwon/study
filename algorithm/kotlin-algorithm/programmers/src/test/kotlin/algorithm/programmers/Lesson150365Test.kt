package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson150365Test : StringSpec({
    "case-1" {
        val n = 3
        val m = 4
        val x = 2
        val y = 3
        val r = 3
        val c = 1
        val k = 5

        val actual = Lesson150365().solution(n, m, x, y, r, c, k)

        actual shouldBe "dllrl"
    }

    "case-2" {
        val n = 2
        val m = 2
        val x = 1
        val y = 1
        val r = 2
        val c = 2
        val k = 2

        val actual = Lesson150365().solution(n, m, x, y, r, c, k)

        actual shouldBe "dr"
    }

    "case-3" {
        val n = 3
        val m = 3
        val x = 1
        val y = 2
        val r = 3
        val c = 3
        val k = 4

        val actual = Lesson150365().solution(n, m, x, y, r, c, k)

        actual shouldBe "impossible"
    }
})
