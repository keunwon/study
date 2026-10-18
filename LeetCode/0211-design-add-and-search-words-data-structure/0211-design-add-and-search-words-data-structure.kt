class WordDictionary() {
    private val children = arrayOfNulls<WordDictionary>(26)
    private var isTerminated = false

    fun addWord(word: String) {
        var cur = this
        for (c in word) {
            val idx = c - 'a'
            if (cur.children[idx] == null) {
                cur.children[idx] = WordDictionary()
            }
            cur = cur.children[idx]!!
        }
        cur.isTerminated = true
    }

    fun search(word: String): Boolean {
        return search(word, 0, this)
    }

    private fun search(word: String, startIdx: Int, curWordDictionary: WordDictionary): Boolean {
        var cur = curWordDictionary
        for (i in startIdx until word.length) {
            val c = word[i]
            if (c == '.') {
                for (child in cur.children) {
                    if (child != null && search(word, i + 1, child)) {
                        return true
                    }
                }
                return false
            } else {
                cur = cur.children[c - 'a'] ?: return false
            }
        }
        return cur.isTerminated
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * var obj = WordDictionary()
 * obj.addWord(word)
 * var param_2 = obj.search(word)
 */