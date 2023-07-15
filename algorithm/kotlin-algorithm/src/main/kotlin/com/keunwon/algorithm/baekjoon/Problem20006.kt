package com.keunwon.algorithm.baekjoon

/**
 * Title: 랭킹전 대기열
 * Level: 실버-2
 **/
class Problem20006 {
    fun solution(m: Int, players: Array<Pair<Int, String>>): Array<String> {
        val rooms = mutableListOf<Room>()

        for (user in players.map { User(it.first, it.second) }) {
            val room = rooms.firstOrNull { it.check(user.level, m) }
            room?.users?.add(user) ?: rooms.add(Room(user))
        }
        return rooms.map { room ->
            val prefix = if (room.size == m) "Started!" else "Waiting!"
            "$prefix\n" + room.users.sorted().joinToString("\n")
        }.toTypedArray()
    }

    private data class Room(val levelRange: IntRange, val users: MutableList<User>) {
        val size: Int
            get() = users.size

        constructor(user: User) : this(user.level - 10..user.level + 10, mutableListOf(user))

        fun check(level: Int, limit: Int): Boolean {
            return level in levelRange && users.size < limit
        }
    }

    private data class User(val level: Int, val nickname: String) : Comparable<User> {
        override fun compareTo(other: User): Int = nickname.compareTo(other.nickname)

        override fun toString(): String = "$level $nickname"
    }
}

fun main() {
    val (p, m) = readLine()!!.split(" ").map { it.toInt() }
    val players = Array(p) { readLine()!!.split(" ").let { it[0].toInt() to it[1] } }

    Problem20006().solution(m, players).also { println(it.joinToString("\n")) }
}
