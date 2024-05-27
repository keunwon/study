package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem20006 {
    fun solution(m: Int, players: Array<Pair<Int, String>>): Array<String> {
        val rooms = mutableListOf<Room>()

        for ((level, id) in players) {
            val user = User(id, level)
            val room = rooms.find { it.isEnter(user) }

            if (room != null) {
                room.enter(user)
            } else {
                val newRoom = Room(IntRange(level - 10, level + 10), m, mutableListOf(user))
                rooms.add(newRoom)
            }
        }
        return rooms.map { room ->
            val prefix = if (room.isFull()) "Started!" else "Waiting!"
            "$prefix\n${room.userList()}"
        }.toTypedArray()
    }

    private data class Room(
        val levelLimit: IntRange,
        val userLimit: Int,
        val users: MutableList<User>,
    ) {
        fun isFull(): Boolean = users.size == userLimit

        fun isEnter(user: User): Boolean =
            user.level in levelLimit && users.size < userLimit

        fun enter(user: User) {
            if (isEnter(user)) users.add(user)
        }

        fun userList(): String =
            users.sortedBy { it.id }.joinToString(System.lineSeparator()) { "${it.level} ${it.id}" }
    }

    private data class User(val id: String, val level: Int)
}


fun main() {
    val (p, m) = readln().split(" ").map { it.toInt() }
    val players = Array(p) {
        val st = StringTokenizer(readln())
        Pair(st.nextToken().toInt(), st.nextToken())
    }

    Problem20006().solution(m, players).also { println(it.joinToString(System.lineSeparator())) }
}
