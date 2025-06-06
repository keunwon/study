package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson72414Test : StringSpec({
    "case-1" {
        val playtime = "02:03:55"
        val advTime = "00:14:15"
        val logs = arrayOf(
            "01:20:15-01:45:14",
            "00:40:31-01:00:00",
            "00:25:50-00:48:29",
            "01:30:59-01:53:29",
            "01:37:44-02:02:30"
        )

        val actual = Lesson72414().solution(playtime, advTime, logs)

        actual shouldBe "01:30:59"
    }

    "case-2" {
        val playTime = "99:59:59"
        val advTime = "25:00:00"
        val logs = arrayOf("69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00")

        val actual = Lesson72414().solution(playTime, advTime, logs)

        actual shouldBe "01:00:00"
    }

    "case-3" {
        val playTime = "50:00:00"
        val advTime = "50:00:00"
        val logs = arrayOf("15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45")

        val actual = Lesson72414().solution(playTime, advTime, logs)

        actual shouldBe "00:00:00"
    }
})
