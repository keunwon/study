package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson147355Test : StringSpec({
    "case-1" {
        val t = "3141592"
        val p = "271"

        val actual = Lesson147355().solution(t, p)

        actual shouldBe 2
    }

    "case-2" {
        val t = "500220839878"
        val p = "7"

        val actual = Lesson147355().solution(t, p)

        actual shouldBe 8
    }

    "case-3" {
        val t = "10203"
        val p = "15"

        val actual = Lesson147355().solution(t, p)

        actual shouldBe 3
    }
})
