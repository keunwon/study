package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42579Test : StringSpec({
    "case-1" {
        val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
        val plays = intArrayOf(500, 600, 150, 800, 2500)

        val actual = Lesson42579().solution(genres, plays)

        actual shouldBe intArrayOf(4, 1, 3, 0)
    }
})
