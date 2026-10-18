class Trie() {
    val children = arrayOfNulls<Trie>(26)
    var isTerminated = false

    fun insert(word: String) {
        var cur = this
        for (c in word) {
            val idx = c - 'a'
            if (cur.children[idx] == null) {
                cur.children[idx] = Trie()
            }
            cur = cur.children[idx]!!
        }
        cur.isTerminated = true;
    }

    fun search(word: String): Boolean {
        var cur = this
        for (c in word) {
            cur = cur.children[c - 'a'] ?: return false
        }
        return cur.isTerminated
    }

    fun startsWith(prefix: String): Boolean {
        var cur = this
        for (c in prefix) {
            cur = cur.children[c - 'a'] ?: return false
        }
        return true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */