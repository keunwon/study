package algorithm.programmers

/**
 * <p>
 * 이름: [PCCP 기출문제] 4번 / 수식 복원하기
 * 난이도: Lavel-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/340210">[PCCP 기출문제] 4번 / 수식 복원하기 (Lavel-3)</a>
 **/
class Lesson340210 {
    fun solution(expressions: Array<String>): Array<String> {
        val bases = mutableSetOf<Int>()
        val validExpressions = mutableListOf<MutableList<String>>()
        val inValidExpressions = mutableListOf<MutableList<String>>()
        var minBase = 0

        for (ex in expressions) {
            val arr = ex.split(" ").toMutableList()
            val max = if (arr[4] != "X") {
                "${arr[0]}${arr[2]}${arr[4]}".maxOf { it } - '0'
            } else {
                "${arr[0]}${arr[2]}".maxOf { it } - '0'
            }
            minBase = minBase.coerceAtLeast(max)

            if (ex.contains("X")) {
                inValidExpressions.add(arr)
            } else {
                validExpressions.add(arr)
            }
        }

        for (base in minBase + 1..9) {
            val valid = validExpressions.all { ex ->
                val a = ex[0].toInt(base)
                val b = ex[2].toInt(base)
                val result = ex[4].toInt(base)
                val calcNum = when (ex[1]) {
                    "+" -> a + b
                    "-" -> a - b
                    else -> error("not support")
                }
                calcNum == result
            }
            if (valid) bases.add(base)
        }

        val result = Array(inValidExpressions.size) { "" }
        for ((i, ex) in inValidExpressions.withIndex()) {
            val calcNumbers = bases.map { base ->
                val a = ex[0].toInt(base)
                val b = ex[2].toInt(base)
                val calNumber = if (ex[1] == "+") a + b else a - b
                calNumber.toString(base)
            }
            ex[4] = if (calcNumbers.toSet().size == 1) {
                calcNumbers[0].ifBlank { "0" }
            } else {
                "?"
            }
            result[i] = ex.joinToString(" ")
        }
        return result
    }
}
