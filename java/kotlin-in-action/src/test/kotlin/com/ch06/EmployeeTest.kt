package com.ch06

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

internal class EmployeeTest : DescribeSpec({

    describe("manageName") {
        context("매니저 이름을 출력합니다") {
            val ceo = Employee("Da Boss", null)
            val developer = Employee("Bob Smith", ceo)

            manageName(developer) shouldBe ceo.name
            manageName(ceo).shouldBeNull()
        }
    }
})
