package com.keunwon.algorithm.againresolve

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.ceil

class ALessons92341 {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val parking = mutableMapOf<String, LocalTime>()
        val parkingFees = mutableMapOf<String, Long>()

        for (record in records) {
            val (timeToString, no, option) = record.split(" ")
            val time = LocalTime.parse(timeToString, TIME_FORMAT)

            if (option == "IN") {
                parking[no] = time
                continue
            }
            val diff = getDiff(parking.getValue(no), time)
            parkingFees[no] = parkingFees.getOrDefault(no, 0L) + diff
            parking.remove(no)
        }

        for ((no, time) in parking) {
            val diff = getDiff(time, LocalTime.of(23, 59))
            parkingFees[no] = parkingFees.getOrDefault(no, 0L) + diff
        }

        val (basicTime, basicRate, extraTime, extraRate) = fees
        return parkingFees.toList()
            .sortedBy { it.first }
            .map { (_, time) ->
                if (basicTime >= time) basicRate
                else {
                    val multiplicand = ceil((time - basicTime).toFloat() / extraTime).toInt()
                    basicRate + extraRate * multiplicand
                }
            }.toIntArray()
    }

    private fun getDiff(start: LocalTime, end: LocalTime): Long = ChronoUnit.MINUTES.between(start, end)

    companion object {
        private val TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm")
    }
}

fun main() {
    ALessons92341().solution(
        intArrayOf(180, 5000, 10, 600),
        arrayOf(
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT",
        )
    ).let { println(it.contentToString()) }

    ALessons92341().solution(
        intArrayOf(120, 0, 60, 591),
        arrayOf("16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN")
    ).let { println(it.contentToString()) }

    ALessons92341().solution(
        intArrayOf(1, 461, 1, 10),
        arrayOf("00:00 1234 IN")
    ).let { println(it.contentToString()) }
}
