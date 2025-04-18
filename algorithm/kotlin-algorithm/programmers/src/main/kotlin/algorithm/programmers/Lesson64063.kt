package algorithm.programmers

class Lesson64063 {
    private val rooms = mutableMapOf<Long, Long>()

    fun solution(k: Long, room_number: LongArray): LongArray {
        return room_number.map { findRoom(it) }.toLongArray()
    }

    private fun findRoom(room: Long): Long {
        if (!rooms.containsKey(room)) {
            rooms[room] = room + 1
            return room
        }
        return findRoom(rooms.getValue(room)).also { rooms[room] = it }
    }
}
