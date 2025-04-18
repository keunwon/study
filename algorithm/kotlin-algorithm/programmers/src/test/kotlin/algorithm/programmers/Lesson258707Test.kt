package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson258707Test : StringSpec({
    "case-1" {
        val coin = 4
        val cards = intArrayOf(3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4)

        val actual = Lesson258707().solution(coin, cards)

        actual shouldBe 5
    }

    "case-2" {
        val coin = 3
        val cards = intArrayOf(1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12)

        val actual = Lesson258707().solution(coin, cards)

        actual shouldBe 2
    }

    "case-3" {
        val coin = 2
        val cards = intArrayOf(5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7)

        val actual = Lesson258707().solution(coin, cards)

        actual shouldBe 4
    }

    "case-4" {
        val coin = 10
        val cards = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)

        val actual = Lesson258707().solution(coin, cards)

        actual shouldBe 1
    }
})
