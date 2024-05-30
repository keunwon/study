package com.keunwon.algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson42888Test : StringSpec({
    "case-1" {
        val record = arrayOf(
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        )

        val actual = Lesson42888().solution(record)

        actual shouldBe arrayOf("Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다.")
    }
})
