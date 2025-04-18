package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42628Test : StringSpec({
    "case-1" {
        val operations = arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1")
        val actual = Lesson42628().solution(operations)
        actual shouldBe intArrayOf(0, 0)
    }

    "case-2" {
        val operations = arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")
        val actual = Lesson42628().solution(operations)
        actual shouldBe intArrayOf(333, -45)
    }
})
