package com.keunwon.algorithm.programmers

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.ceil

/**
 * Title: 주차 요금 계산
 * Level: 2
 **/
class Lessons92341 {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val parking = mutableMapOf<String, LocalTime>()
        val parkingMinutes = mutableMapOf<String, Int>()
        val getDiff = { end: LocalTime, begin: LocalTime ->
            (end.toSecondOfDay() - begin.toSecondOfDay()) / 60
        }

        for (r in records) {
            val (timeToString, no, option) = r.split(" ")
            val time = LocalTime.parse(timeToString, timeFormatter)

            if (option == "IN") {
                parking[no] = time
                continue
            }

            val diff = getDiff(time, parking.getValue(no))
            parkingMinutes[no] = parkingMinutes.getOrDefault(no, 0) + diff
            parking.remove(no)
        }

        for ((no, time) in parking) {
            val diff = getDiff(LocalTime.of(23, 59), time)
            parkingMinutes[no] = parkingMinutes.getOrDefault(no, 0) + diff
        }

        val (baseTime, basePrice, extraTime, extraPrice) = fees
        return parkingMinutes.toList()
            .sortedBy { it.first }
            .map { (_, minutes) ->
                if (minutes <= baseTime) basePrice
                else {
                    val rest = ceil((minutes - baseTime).toDouble() / extraTime).toInt()
                    basePrice + extraPrice * rest
                }
            }.toIntArray()
    }
}
