package algorithm.baekjoon

/**
 * <p>
 * 이름: 랭킹전 대기열
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20006">랭킹전 대기열 (실버-2)</a>
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
            "$prefix\n" + room.users.sorted().joinToString(System.lineSeparator())
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
    val (p, m) = readln().split(" ").map { it.toInt() }
    val players = Array(p) { readln().split(" ").let { it[0].toInt() to it[1] } }

    Problem20006().solution(m, players).also { println(it.joinToString(System.lineSeparator())) }
}
