package algorithm.leetcode

class `412_Fizz Buzz` {
    fun fizzBuzz(n: Int): List<String> {
        return (1..n).map { num ->
            val mod1 = num % 3
            val mod2 = num % 5

            if (mod1 == 0 && mod2 == 0) {
                "FizzBuzz"
            } else if (mod1 == 0) {
                "Fizz"
            } else if (mod2 == 0) {
                "Buzz"
            } else {
                "$num"
            }
        }
    }
}
