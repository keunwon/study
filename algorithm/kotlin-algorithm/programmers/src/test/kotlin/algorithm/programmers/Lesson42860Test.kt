package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42860Test : StringSpec({
    "case-1" {
        val name = "JEROEN"
        val actual = Lesson42860().solution(name)
        actual shouldBe 56
    }

    "case-2" {
        val name = "JAN"
        val actual = Lesson42860().solution(name)
        actual shouldBe 23
    }
})
