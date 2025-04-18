package algorithm.programmers

class Lesson60059 {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val newLock = expendingLock(key, lock)
        repeat(4) {
            if (check(key, lock, newLock)) return true
            rotate(key)
        }
        return false
    }

    private fun rotate(key: Array<IntArray>) {
        val tmpArr = Array(key.size) { IntArray(key[0].size) }

        for (i in tmpArr.indices) {
            for (j in tmpArr[0].indices) {
                tmpArr[i][j] = key[key.lastIndex - j][i]
            }
        }

        for (i in key.indices) {
            for (j in key[0].indices) {
                key[i][j] = tmpArr[i][j]
            }
        }
    }

    private fun check(key: Array<IntArray>, lock: Array<IntArray>, newLock: Array<IntArray>): Boolean {
        val size = key.lastIndex + lock.size
        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in key.indices) {
                    for (l in key[0].indices) {
                        newLock[i + k][j + l] += key[k][l]
                    }
                }

                var flag = true
                for (k in lock.indices) {
                    for (l in lock[0].indices) {
                        if (newLock[k + key.lastIndex][l + key[0].lastIndex] != 1) {
                            flag = false
                        }
                    }
                }
                if (flag) return true

                for (k in key.indices) {
                    for (l in key[0].indices) {
                        newLock[i + k][j + l] -= key[k][l]
                    }
                }
            }
        }
        return false
    }

    private fun expendingLock(key: Array<IntArray>, lock: Array<IntArray>): Array<IntArray> {
        val size = lock.size + (key.size * 2) - 2
        val newLock = Array(size) { IntArray(size) }

        for (i in lock.indices) {
            for (j in lock[0].indices) {
                newLock[i + key.lastIndex][j + key[0].lastIndex] = lock[i][j]
            }
        }
        return newLock
    }
}
