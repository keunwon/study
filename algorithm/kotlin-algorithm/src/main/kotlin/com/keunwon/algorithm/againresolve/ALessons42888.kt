package com.keunwon.algorithm.againresolve

class ALessons42888 {
    fun solution(record: Array<String>): Array<String> {
        val userNames = record
            .filterNot { it.startsWith("Leave") }
            .associate { s -> s.split(" ").let { it[1] to it[2] } }
        return record
            .filterNot { it.startsWith("Change") }
            .map { r ->
                val (option, uid) = r.split(" ")
                if (option.startsWith("Enter")) "${userNames[uid]}님이 들어왔습니다."
                else "${userNames[uid]}님이 나갔습니다."
            }.toTypedArray()
    }
}

fun main() {
    ALessons42888().solution(
        arrayOf(
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        )
    ).forEach(::println)
}
