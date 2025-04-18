package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Lesson181916Test : StringSpec({
    "기본 테스트" {
        forAll(
            row(2, 2, 2, 2, 2222),
            row(4, 1, 4, 4, 1681),
            row(6, 3, 3, 6, 27),
            row(2, 5, 2, 6, 30),
            row(6, 4, 2, 5, 2),
        ) { a, b, c, d, result ->
            val actual = Lesson181916().solution(a, b, c, d)
            actual shouldBe result
        }
    }
})
