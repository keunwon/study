package algorithm.programmers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Lesson77486Test : StringSpec({
    "case-1" {
        val enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
        val referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
        val seller = arrayOf("young", "john", "tod", "emily", "mary")
        val amount = intArrayOf(12, 4, 2, 5, 10)

        val actual = Lesson77486().solution(enroll, referral, seller, amount)

        actual shouldBe intArrayOf(360, 958, 108, 0, 450, 18, 180, 1080)
    }

    "case-2" {
        val enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
        val referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
        val seller = arrayOf("sam", "emily", "jaimie", "edward")
        val amount = intArrayOf(2, 3, 5, 4)

        val actual = Lesson77486().solution(enroll, referral, seller, amount)

        actual shouldBe intArrayOf(0, 110, 378, 180, 270, 450, 0, 0)
    }
})
