package com.ch04

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class CountingSetTest : DescribeSpec({

    describe("add") {
        context("입력된 컬렉션 개수를 반환한다") {
            val set = CountingSet<String>()
            set.add("홍길동")
            set.add("세종대왕")

            set.shouldHaveSize(2)
        }
    }

    describe("addAll") {
        context("입력된 collection 개수를 반환한다") {
            val set = CountingSet<String>()
            val list1 = hashSetOf("홍길동", "세종대왕", "이순신")
            val list2 = hashSetOf("apple", "banana")

            set.addAll(list1)
            set.addAll(list2)

            set.objectAdded shouldBe 5
            set.shouldHaveSize(5)
                .shouldContainExactlyInAnyOrder("홍길동", "세종대왕", "이순신", "apple", "banana")
        }
    }
})