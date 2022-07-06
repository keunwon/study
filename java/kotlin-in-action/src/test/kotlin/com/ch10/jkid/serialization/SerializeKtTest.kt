package com.ch10.jkid.serialization

import com.ch10.jkid.CustomSerializer
import com.ch10.jkid.JKidException
import com.ch10.jkid.JsonExclude
import com.ch10.jkid.JsonName
import com.ch10.jkid.ValueSerializer
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

internal data class SingleStringProp(val s: String)
internal data class TwoIntProp(val i1: Int, val i2: Int)
internal data class TwoBoolProp(val b1: Boolean, val b2: Boolean)
internal data class SingleObjectProp(val o: SingleStringProp)
internal data class SingleListProp(val o: List<String?>)
internal data class SingleAnnotatedStringProp(@JsonName("q") val s: String)
internal data class SingleCustomSerializedProp(@CustomSerializer(NumberSerializer::class) val x: Int)
internal data class TwoPropsOneExcluded(val s: String, @JsonExclude val x: String = "")

class NumberSerializer : ValueSerializer<Int> {
    override fun toJsonValue(value: Int): Any? = when (value) {
        0 -> "ZERO"
        1 -> "ONE"
        else -> "?"
    }

    override fun fromJsonValue(jsonValue: Any?): Int = when (jsonValue) {
        "ZERO" -> 0
        "ONE" -> 1
        else -> throw JKidException("Unexpected value $jsonValue")
    }
}
