package algorithm.programmers

class Lesson340213 {
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
        val videoMinute = video_len.toMinute()
        val opEndSecond = op_end.toMinute()
        val openingMinute = (op_start.toMinute()..opEndSecond)
        var cur = pos.toMinute()

        if (cur in openingMinute) cur = opEndSecond

        commands.forEach { command ->
            when (command) {
                "prev" -> cur = (cur - 10).coerceAtLeast(0)
                "next" -> cur = (cur + 10).coerceAtMost(videoMinute)
            }
            if (cur in openingMinute) cur = opEndSecond
        }

        return "%02d:%02d".format(cur / 60, cur % 60)
    }

    private fun String.toMinute(): Int {
        val (h, m) = split(":").map { it.toInt() }
        return h * 60 + m
    }
}
