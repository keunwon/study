package com.keunwon.algorithm.programmers

/**
 * Title: 호텔 방 배정
 * Level: 4
 **/
class Lessons64063 {
    private val parents = hashMapOf<Long, Long>()

    fun solution(k: Long, room_number: LongArray): LongArray {
        return room_number.map(this::findRoom).toLongArray()
    }

    private fun findRoom(room: Long): Long {
        if (!parents.containsKey(room)) {
            parents[room] = room + 1
            return room
        }
        return findRoom(parents.getValue(room)).also { parents[room] = it }
    }
}

fun main() {
    Lessons64063().solution(100, longArrayOf(1, 1, 1)).also { println(it.contentToString()) }
}
