package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson92334Test : StringSpec({
    "case-1" {
        val id_list = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi")
        val k = 2

        val actual = Lesson92334().solution(id_list, report, k)

        actual shouldBe intArrayOf(2, 1, 1, 0)
    }

    "case-2" {
        val id_list = arrayOf("con", "ryan")
        val report = arrayOf("ryan con", "ryan con", "ryan con", "ryan con")
        val k = 3

        val actual = Lesson92334().solution(id_list, report, k)

        actual shouldBe intArrayOf(0, 0)
    }
})
