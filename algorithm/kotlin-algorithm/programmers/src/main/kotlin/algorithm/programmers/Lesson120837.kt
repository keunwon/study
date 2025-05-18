package algorithm.programmers

class Lesson120837 {
    fun solution(hp: Int): Int {
        var ret = hp
        var result = 0

        result += ret / 5
        ret %= 5
        result += ret / 3
        ret %= 3
        result += ret
        return result
    }
}
