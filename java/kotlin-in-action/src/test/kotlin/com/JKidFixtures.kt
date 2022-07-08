package com

import com.ch10.jkid.CustomSerializer
import com.ch10.jkid.DeserializeInterface
import com.ch10.jkid.JKidException
import com.ch10.jkid.JsonExclude
import com.ch10.jkid.JsonName
import com.ch10.jkid.ValueSerializer
import com.ch10.jkid.exercise.DateFormat
import java.time.LocalDate

data class SingleStringProp(val s: String)
data class SingleNullableStringProp(val s: String?)
data class TwoIntProp(val i1: Int, val i2: Int)
data class TwoBoolProp(val b1: Boolean, val b2: Boolean)
data class SingleObjectProp(val o: SingleStringProp)
data class SingleListProp(val o: List<String?>)
data class SingleOptionalProp(val s: String = "foo")
data class SingleObjectListProp(val o: List<SingleStringProp>)
data class SingleAnnotatedStringProp(@JsonName("q") val s: String)
data class SingleCustomSerializedProp(@CustomSerializer(NumberSerializer::class) val x: Int)
data class TwoPropsOneExcluded(val s: String, @JsonExclude val x: String = "")
data class SingleDefaultDateFormatAnnotatedLocalProp(@DateFormat val now: LocalDate)
data class SingleDateFormatAnnotatedLocalProp(@DateFormat("dd-MM-yyyy") val now: LocalDate)
data class ListOfInts(val ints: List<Int>)

interface ValueIntf { val name: String }
data class ValueImpl(override val name: String) : ValueIntf
data class ValueHolder(@DeserializeInterface(ValueImpl::class) val value: ValueIntf)

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
