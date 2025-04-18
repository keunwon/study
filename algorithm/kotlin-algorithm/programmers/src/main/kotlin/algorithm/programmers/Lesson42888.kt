package algorithm.programmers

import java.util.*

class Lesson42888 {
    fun solution(record: Array<String>): Array<String> {
        val users = mutableMapOf<String, String>().apply {
            record.forEach { r ->
                val st = StringTokenizer(r)
                if (st.nextToken() in arrayOf("Enter", "Change")) {
                    this[st.nextToken()] = st.nextToken()
                }
            }
        }

        val infoMessages = mapOf(
            "Enter" to "들어왔습니다.",
            "Leave" to "나갔습니다.",
        )
        return record.mapNotNull { r ->
            val (option, id) = r.split(" ")
            infoMessages[option]?.let { message -> "${users.getValue(id)}님이 $message" }
        }.toTypedArray()
    }
}
