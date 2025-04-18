package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson64064Test : StringSpec({
    "case-1" {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("fr*d*", "abc1**")

        val actual = Lesson64064().solution(user_id, banned_id)

        actual shouldBe 2
    }

    "case-2" {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("*rodo", "*rodo", "******")

        val actual = Lesson64064().solution(user_id, banned_id)

        actual shouldBe 2
    }

    "case-3" {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("fr*d*", "*rodo", "******", "******")

        val actual = Lesson64064().solution(user_id, banned_id)

        actual shouldBe 3
    }
})
