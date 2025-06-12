package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson160586Test : StringSpec({
    "case-1" {
        val actual = Lesson160586().solution(
            keymap = arrayOf("ABACD", "BCEFD"),
            targets = arrayOf("ABCD", "AABB"),
        )
        actual shouldBe intArrayOf(9, 4)
    }

    "case-2" {
        val actual = Lesson160586().solution(
            keymap = arrayOf("AA"),
            targets = arrayOf("B"),
        )
        actual shouldBe intArrayOf(-1)
    }

    "case-3" {
        val actual = Lesson160586().solution(
            keymap = arrayOf("AGZ", "BSSS"),
            targets = arrayOf("ASA", "BGZ"),
        )
        actual shouldBe intArrayOf(4, 6)
    }
})
