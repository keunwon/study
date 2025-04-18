package algorithm.programmers

class Lesson12985 {
    fun solution(n: Int, a: Int, b: Int): Int {
        var aa = a
        var bb = b
        var answer = 0

        while (aa != bb) {
            aa = (aa / 2) + (aa % 2)
            bb = (bb / 2) + (bb % 2)
            ++answer
        }
        return answer
    }
}
