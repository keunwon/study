package algorithm.programmers

/**
 * <p>
 * 이름: n + 1 카드게임
 * 난이도: Level-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258707">n + 1 카드게임( Level-3)</a>
 **/
class Lesson258707 {
    fun solution(coin: Int, cards: IntArray): Int {
        val hand = BooleanArray(cards.size + 1)
        val discount = BooleanArray(cards.size + 1)
        var ret = coin
        var round = 1

        for (i in 0 until cards.size / 3) {
            hand[cards[i]] = true
            hand[cards[i + 1]] = true
        }

        loop@ for (i in cards.size / 3 until cards.size step 2) {
            if (ret > 0) {
                hand[cards[i]] = true
                hand[cards[i + 1]] = true
            }

            for (j in 0 until cards.size / 3) {
                for (k in j + 1 until cards.size / 3) {
                    val a = cards[j]
                    val b = cards[k]

                    if (a + b == cards.size + 1 && !discount[a] && !discount[b]) {
                        discount[a] = true
                        discount[b] = true
                        ++round
                        continue@loop
                    }
                }
            }

            for (j in 0 until i + 2) {
                for (k in j + 1 until i + 2) {
                    val a = cards[j]
                    val b = cards[k]

                    if (a + b == cards.size + 1 && !discount[a] && !discount[b]) {
                        discount[a] = true
                        discount[b] = true

                        if (j >= cards.size / 3) --ret
                        if (k >= cards.size / 3) --ret

                        if (ret < 0) {
                            break@loop
                        } else {
                            ++round
                            continue@loop
                        }
                    }
                }
            }

            break
        }
        return round
    }
}
