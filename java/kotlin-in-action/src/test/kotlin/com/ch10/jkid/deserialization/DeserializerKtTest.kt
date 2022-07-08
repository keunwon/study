package com.ch10.jkid.deserialization

import com.ListOfInts
import com.SingleAnnotatedStringProp
import com.SingleCustomSerializedProp
import com.SingleListProp
import com.SingleNullableStringProp
import com.SingleObjectListProp
import com.SingleObjectProp
import com.SingleOptionalProp
import com.SingleStringProp
import com.TwoBoolProp
import com.TwoIntProp
import com.ValueHolder
import com.ch10.jkid.JKidException
import io.kotest.assertions.throwables.shouldThrowExactlyUnit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class DeserializerKtTest : DescribeSpec({

    describe("deserialize") {
        it("testSimple") {
            val result = deserialize<SingleStringProp>("""{"s": "x"}""")
            "x" shouldBe result.s
        }

        it("testIntLong") {
            val result = deserialize<TwoIntProp>("""{"i1": 42, "i2": 239}""")
            result.i1 shouldBe 42
            result.i2 shouldBe 239
        }

        it("testTwoBools") {
            val result = deserialize<TwoBoolProp>("""{"b1": true, "b2": false}""")
            result.b1.shouldBeTrue()
            result.b2.shouldBeFalse()
        }

        it("testNullableString") {
            val result = deserialize<SingleNullableStringProp>("""{"s": null}""")
            result.s shouldBe null
        }

        it("testObject") {
            val result = deserialize<SingleObjectProp>("""{"o": {"s": "x"}}""")
            println(result.o.s)
        }

        it("testList") {
            val result = deserialize<SingleListProp>("""{"o": ["a", "b"]}""")
            result.o shouldHaveSize 2
            result.o[0] shouldBe "a"
            result.o[1] shouldBe "b"
        }

        it("testNullableList") {
            val result = deserialize<SingleListProp>("""{"o": [null, "b"]}""")
            result.o shouldHaveSize 2
            result.o[0] shouldBe null
            result.o[1] shouldBe "b"
        }

        it("testObjectList") {
            val result = deserialize<SingleObjectListProp>("""{"o": [{"s": "a"}, {"s": "b"}]}""")
            result.o shouldHaveSize 2
            result.o[0].s shouldBe "a"
            result.o[1].s shouldBe "b"
        }

        it("testOptionalArg") {
            val result = deserialize<SingleOptionalProp>("{}")
            result.s shouldBe "foo"
        }

        it("testJsonName") {
            val result = deserialize<SingleAnnotatedStringProp>("""{"q": "x"}""")
            result.s shouldBe "x"
        }

        it("testCustomDeserializer") {
            val result = deserialize<SingleCustomSerializedProp>("""{"x": "ONE"}""")
            result.x shouldBe 1
        }

        it("testTimestampSerializer") {
            val result = deserialize<ValueHolder>("""{"value": {"name": "Foo"}}""")
            result.value.name shouldBe "Foo"
        }

        it("testPropertyTypeMismatch") {
            shouldThrowExactlyUnit<JKidException> {
                deserialize<SingleStringProp>("""{"s": 1}""")
            }
        }

        it("testPropertyTypeMismatchNull") {
            shouldThrowExactlyUnit<JKidException> {
                deserialize<SingleStringProp>("""{"s": null}""")
            }
        }

        it("testMissingPropertyException") {
            shouldThrowExactlyUnit<JKidException> {
                deserialize<SingleStringProp>("{}")
            }
        }

        it("testListOfInts") {
            val result = deserialize<ListOfInts>("""{"ints": [42]}""")
            result.ints shouldContainExactly listOf(42)
        }

        it("testObjectForListOfInts") {
            shouldThrowExactlyUnit<JKidException> {
                deserialize<ListOfInts>("""{"ints": {"a": 42}}""")
            }
        }
    }
})
