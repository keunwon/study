package algorithm.programmers

class Lesson258707_2 {
    fun solution(coin: Int, cards: IntArray): Int {
        val hand = mutableSetOf<Int>().apply {
            for (i in 0 until cards.size / 3) {
                add(cards[i])
            }
        }
        val addHand = mutableSetOf<Int>()
        var ret = coin
        var round = 1
        val target = cards.size + 1

        for (i in cards.size / 3 until cards.size step 2) {
            addHand.add(cards[i])
            addHand.add(cards[i + 1])

            if (removeCardIfMatch(hand, hand, target)) {
                ++round
            } else if (ret > 0 && removeCardIfMatch(hand, addHand, target)) {
                --ret
                ++round
            } else if (ret > 1 && removeCardIfMatch(addHand, addHand, target)) {
                ret -= 2
                ++round
            } else {
                break
            }
        }

        return round
    }

    private fun removeCardIfMatch(card1: MutableSet<Int>, card2: MutableSet<Int>, target: Int): Boolean {
        for (n in card1) {
            val m = target - n
            if (card2.contains(m)) {
                card1.remove(n)
                card2.remove(m)
                return true
            }
        }
        return false
    }
}
