package com.keunwon.algorithm.programmers

class Lesson72412_2 {
    private val search = hashMapOf<String, MutableList<Int>>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        info.forEach { combination(0, StringBuilder(), it.split(" ")) }

        search.forEach { it.value.sort() }

        return IntArray(query.size) { index ->
            val (key, point) = query[index].replace(" and ", "").split(" ")
            binarySearch(key, point.toInt())
        }
    }

    private fun binarySearch(key: String, point: Int): Int {
        val list = search[key] ?: return 0
        var l = 0
        var r = list.lastIndex

        while (l <= r) {
            val mid = l + (r - l) / 2

            if (point <= list[mid]) r = mid - 1
            else l = mid + 1
        }
        return list.size - l
    }

    private fun combination(depth: Int, sb: StringBuilder, info: List<String>) {
        if (depth == info.lastIndex) {
            val point = info.last().toInt()
            val key = sb.toString()

            if (search.containsKey(key)) {
                search.getValue(key).add(point)
            } else {
                search[key] = mutableListOf(point)
            }
            return
        }

        sb.append("-")
        combination(depth + 1, sb, info)

        sb.deleteCharAt(sb.lastIndex)
        sb.append(info[depth])
        combination(depth + 1, sb, info)
        sb.deleteRange(sb.length - info[depth].length, sb.length)
    }
}

fun main() {
    val info = arrayOf(
        "java backend junior pizza 150",
        "python frontend senior chicken 210",
        "python frontend senior chicken 150",
        "cpp backend senior pizza 260",
        "java backend junior chicken 80",
        "python backend senior chicken 50"
    )
    val query = arrayOf(
        "java and backend and junior and pizza 100",
        "python and frontend and senior and chicken 200",
        "cpp and - and senior and pizza 250",
        "- and backend and senior and - 150",
        "- and - and - and chicken 100",
        "- and - and - and - 150"
    )

    val actual = Lesson72412_2().solution(info, query)
}
