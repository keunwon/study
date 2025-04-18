package algorithm.baekjoon

/**
 * <p>
 * 이름: 삼각형과 세 변
 * 난이도: 브론즈-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/5073">삼각형과 세 변 (브론즈-3)</a>
 **/
class Problem5073 {
    fun solution(list: List<List<Int>>): Array<String> {
        return Array(list.size) { idx ->
            val arr = list[idx].sorted()
            if (arr[2] >= arr[0] + arr[1]) {
                "Invalid"
            } else {
                when (arr.toSet().size) {
                    1 -> "Equilateral"
                    2 -> "Isosceles"
                    3 -> "Scalene"
                    else -> ""
                }
            }
        }
    }
}

fun main() {
    val list = mutableListOf<List<Int>>().apply {
        System.`in`.bufferedReader().use { br ->
            while (true) {
                val arr = br.readLine().split(" ").map { it.toInt() }
                if (arr.all { it == 0 }) break
                add(arr)
            }
        }
    }

    System.out.bufferedWriter().use { bw ->
        Problem5073().solution(list).forEach {
            bw.write(it)
            bw.newLine()
        }
        bw.flush()
    }
}
