package com.github.keunwon.core.generic.time

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDateTime

class DateTimePeriodTest : StringSpec({
    "2023.01.01 00:00:00 ~ 2023.01.01 00:00:05 사이에 시간은 true 반환한다" {
        val dateTimePeriod = DateTimePeriod.between(
            LocalDateTime.of(2023, 1, 1, 0, 0, 0),
            LocalDateTime.of(2023, 1, 1, 0, 0, 5),
        )

        listOf(
            LocalDateTime.of(2023, 1, 1, 0, 0, 0),
            LocalDateTime.of(2023, 1, 1, 0, 0, 1),
            LocalDateTime.of(2023, 1, 1, 0, 0, 2),
            LocalDateTime.of(2023, 1, 1, 0, 0, 3),
            LocalDateTime.of(2023, 1, 1, 0, 0, 4),
            LocalDateTime.of(2023, 1, 1, 0, 0, 5),
        ).shouldForAll {
            dateTimePeriod.contains(it).shouldBeTrue()
        }
    }

    "2023.01.01 00:00:00 ~ 2024.01.01 00:00:00 사이에 시간이 아니면 false 반환한다" {
        val dateTimePeriod = DateTimePeriod.between(
            LocalDateTime.of(2023, 1, 1, 0, 0, 0),
            LocalDateTime.of(2024, 1, 1, 0, 0, 0),
        )

        listOf(
            LocalDateTime.of(2022, 1, 1, 0, 0, 0),
            LocalDateTime.of(2024, 1, 1, 0, 0, 1),
            LocalDateTime.of(2024, 6, 1, 0, 0, 0),
        ).shouldForAll {
            dateTimePeriod.contains(it).shouldBeFalse()
        }
    }
})
