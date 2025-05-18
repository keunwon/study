package algorithm.programmers

class Lesson133499 {
    fun solution(babbling: Array<String>): Int =
        babbling.count {
            it
                .replace("ayaaya", "1")
                .replace("yeye", "1")
                .replace("woowoo", "1")
                .replace("mama", "1")
                .replace("aya", " ")
                .replace("ye", " ")
                .replace("woo", " ")
                .replace("ma", " ")
                .isBlank()
        }
}
