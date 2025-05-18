package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson181836Test : StringSpec({
    "case-1" {
        val picture = arrayOf(".xx...xx.", "x..x.x..x", "x...x...x", ".x.....x.", "..x...x..", "...x.x...", "....x....")
        val k = 2

        val actual = Lesson181836().solution(picture, k)

        actual shouldBe arrayOf(
            "..xxxx......xxxx..",
            "..xxxx......xxxx..",
            "xx....xx..xx....xx",
            "xx....xx..xx....xx",
            "xx......xx......xx",
            "xx......xx......xx",
            "..xx..........xx..",
            "..xx..........xx..",
            "....xx......xx....",
            "....xx......xx....",
            "......xx..xx......",
            "......xx..xx......",
            "........xx........",
            "........xx........",
        )
    }

    "case-2" {
        val picture = arrayOf("x.x", ".x.", "x.x")
        val k = 3

        val actual = Lesson181836().solution(picture, k)

        actual shouldBe arrayOf(
            "xxx...xxx",
            "xxx...xxx",
            "xxx...xxx",
            "...xxx...",
            "...xxx...",
            "...xxx...",
            "xxx...xxx",
            "xxx...xxx",
            "xxx...xxx",
        )
    }
})
