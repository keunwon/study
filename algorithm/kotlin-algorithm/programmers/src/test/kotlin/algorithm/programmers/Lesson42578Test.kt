package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42578Test : StringSpec({
    "case-1" {
        val clothes = arrayOf(
            arrayOf("yellow_hat", "headgear"),
            arrayOf("blue_sunglasses", "eyewear"),
            arrayOf("green_turban", "headgear")
        )
        val actual = Lesson42578().solution(clothes)
        actual shouldBe 5
    }

    "case-2" {
        val clothes = arrayOf(
            arrayOf("crow_mask", "face"),
            arrayOf("blue_sunglasses", "face"),
            arrayOf("smoky_makeup", "face")
        )
        val actual = Lesson42578().solution(clothes)
        actual shouldBe 3
    }
})
