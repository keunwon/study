package com.keunwon.algorithm.programmers

import kotlin.math.ceil

class Lesson92341 {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val input = mutableMapOf<String, Int>()
        val parking = mutableMapOf<String, Int>()

        for (record in records) {
            val (time, id, option) = record.split(" ")

            if (option == "IN") {
                input[id] = time.toMinutes()
            } else if (option == "OUT") {
                val diff = time.toMinutes() - input.getValue(id)
                parking[id] = parking.getOrDefault(id, 0) + diff
                input.remove(id)
            }
        }
        for ((id, time) in input) {
            val diff = "23:59".toMinutes() - time
            parking[id] = parking.getOrDefault(id, 0) + diff
        }

        val (baseMinutes, baseFee, extraMinutes, extraFee) = fees
        return parking.entries.sortedBy { it.key }.map { (_, time) ->
            if (time <= baseMinutes) baseFee
            else {
                val n = ceil((time - baseMinutes).toDouble() / extraMinutes).toInt()
                baseFee + (extraFee * n)
            }
        }.toIntArray()
    }

    private fun String.toMinutes(): Int {
        val (h, m) = split(":").map { it.toInt() }
        return h * 60 + m
    }
}
