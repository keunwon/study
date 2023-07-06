package com.keunwon.algorithm.programmers

/**
 * Title: 오픈채팅방
 * Level: 2
 **/
class Lessons42888 {
    fun solution(record: Array<String>): Array<String> {
        val names = mutableMapOf<String, String>().apply {
            record.forEach { r ->
                val command = r.split(" ")
                if (command[0] in arrayOf("Enter", "Change")) this[command[1]] = command[2]
            }
        }
        return record
            .filterNot { it.startsWith("Change") }
            .map { r ->
                val (option, uid) = r.split(" ")
                if (option == "Enter") "${names[uid]}님이 들어왔습니다."
                else "${names[uid]}님이 나갔습니다."
            }.toTypedArray()
    }
}
