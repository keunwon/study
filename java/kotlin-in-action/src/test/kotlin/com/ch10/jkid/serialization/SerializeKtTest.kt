package com.ch10.jkid.serialization

import com.SingleAnnotatedStringProp
import com.SingleCustomSerializedProp
import com.SingleListProp
import com.SingleObjectProp
import com.SingleStringProp
import com.TwoBoolProp
import com.TwoIntProp
import com.TwoPropsOneExcluded
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class SerializeKtTest : DescribeSpec({

    describe("serialize") {
        it("testSimple") {
            val result = serialize(SingleStringProp("x"))
            result shouldBe  """{"s": "x"}"""
        }

        it("testTwoInts") {
            val result = serialize(TwoIntProp(1, 2))
            result shouldBe """{"i1": 1, "i2": 2}"""
        }

        it("testTwoBooleans") {
            val result = serialize(TwoBoolProp(true, false))
            result shouldBe """{"b1": true, "b2": false}"""
        }

        it("testObject") {
            val result = serialize(SingleObjectProp(SingleStringProp("x")))
            result shouldBe """{"o": {"s": "x"}}"""
        }

        it("testList") {
            val result = serialize(SingleListProp(listOf("a", "b")))
            result shouldBe """{"o": ["a", "b"]}"""
        }

        it("testJsonName") {
            val result = serialize(SingleAnnotatedStringProp("x"))
            result shouldBe """{"q": "x"}"""
        }

        it("testCustomSerializer") {
            val result = serialize(SingleCustomSerializedProp(1))
            result shouldBe """{"x": "ONE"}"""
        }

        it("testEscapeSequences") {
            val result = serialize(SingleStringProp("\\\""))
            result shouldBe """{"s": "\\\""}"""
        }

        it("testJsonExclude") {
            val result = serialize(TwoPropsOneExcluded("foo", "bar"))
            result shouldBe """{"s": "foo"}"""
        }
    }
})
