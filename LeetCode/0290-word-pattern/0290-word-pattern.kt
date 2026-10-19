class Solution {
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")
        val alphabet = IntArray(26) { -1 }
        val wordMap = HashMap<String, Int>()

        if (pattern.length != words.size) {
            return false
        }

        for (i in pattern.indices) {
            val alphabetIdx = pattern[i] - 'a'
            val key = words[i]

            if (alphabet[alphabetIdx] == -1 && !wordMap.containsKey(key)) {
                alphabet[alphabetIdx] = i
                wordMap[key] = i
            } else if (alphabet[alphabetIdx] != wordMap.getOrDefault(key, -2)) {
                return false
            }
        }
        return true
    }
}