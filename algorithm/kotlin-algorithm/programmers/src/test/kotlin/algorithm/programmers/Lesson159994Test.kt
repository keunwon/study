package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson159994Test : StringSpec({
    "case-1" {
        val cards1 = arrayOf("i", "drink", "water")
        val cards2 = arrayOf("want", "to")
        val goal = arrayOf("i", "want", "to", "drink", "water")

        val actual = Lesson159994().solution(cards1, cards2, goal)

        actual shouldBe "Yes"
    }

    "case-2" {
        val cards1 = arrayOf("i", "water", "drink")
        val cards2 = arrayOf("want", "to")
        val goal = arrayOf("i", "want", "to", "drink", "water")

        val actual = Lesson159994().solution(cards1, cards2, goal)

        actual shouldBe "No"
    }
})
