package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson176963Test : StringSpec({
    "case-1" {
        val name = arrayOf("may", "kein", "kain", "radi")
        val yearning = intArrayOf(5, 10, 1, 3)
        val photo = arrayOf(
            arrayOf("may", "kein", "kain", "radi"),
            arrayOf("may", "kein", "brin", "deny"),
            arrayOf("kon", "kain", "may", "coni")
        )

        val actual = Lesson176963().solution(name, yearning, photo)

        actual shouldBe intArrayOf(19, 15, 6)
    }

    "case-2" {
        val name = arrayOf("kali", "mari", "don")
        val yearning = intArrayOf(11, 1, 55)
        val photo = arrayOf(
            arrayOf("kali", "mari", "don"),
            arrayOf("pony", "tom", "teddy"),
            arrayOf("con", "mona", "don")
        )

        val actual = Lesson176963().solution(name, yearning, photo)

        actual shouldBe intArrayOf(67, 0, 55)
    }

    "case-3" {
        val name = arrayOf("may", "kein", "kain", "radi")
        val yearning = intArrayOf(5, 10, 1, 3)
        val photo = arrayOf(
            arrayOf("may"),
            arrayOf("kein", "deny", "may"),
            arrayOf("kon", "coni")
        )

        val actual = Lesson176963().solution(name, yearning, photo)

        actual shouldBe intArrayOf(5, 15, 0)
    }
})
