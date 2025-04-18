package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson12923KTest : StringSpec({
    "case-1" {
        val begin = 1L
        val end = 10L

        val actual = Lesson12923().solution(begin, end)

        actual shouldBe arrayOf(0, 1, 1, 2, 1, 3, 1, 4, 3, 5)
    }

    "case-2" {
        val begin = 100000014L
        val end = 100000016L

        val actual = Lesson12923().solution(begin, end)

        actual shouldBe arrayOf(6, 5, 6250001);
    }
})
