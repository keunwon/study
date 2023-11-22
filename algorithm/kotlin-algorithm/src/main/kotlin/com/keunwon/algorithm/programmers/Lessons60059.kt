package com.keunwon.algorithm.programmers

/**
 * Title: 자물쇠와 열쇠
 * Level: 3
 **/
class Lessons60059 {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val newLock = expandingLock(lock, key)

        repeat(4) {
            if (check(newLock, key, lock)) return true
            rotate(key)
        }
        return false
    }

    private fun check(newLock: Array<IntArray>, key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val size = newLock.size - key.lastIndex
        for (i in 0 until size) {
            for (j in 0 until size) {

                for (k in key.indices) {
                    for ((l, num) in key[k].withIndex()) {
                        newLock[i + k][j + l] += num
                    }
                }

                var flag = true

                for (k in key.lastIndex until key.lastIndex + lock.size) {
                    for (l in key[0].lastIndex until key[0].lastIndex + lock.size) {
                        if (newLock[k][l] != 1) {
                            flag = false
                            break
                        }
                    }
                    if (!flag) break
                }
                if (flag) return true

                for (k in key.indices) {
                    for ((l, num) in key[k].withIndex()) {
                        newLock[i + k][j + l] -= num
                    }
                }
            }
        }
        return false
    }

    private fun rotate(key: Array<IntArray>) {
        val tmpArr = Array(key.size) { IntArray(key[0].size) }
        for (i in key.indices) {
            for (j in key[0].indices) {
                tmpArr[i][j] = key[key.lastIndex - j][i]
            }
        }
        for (i in key.indices) {
            System.arraycopy(tmpArr[i], 0, key[i], 0, key[i].size)
        }
    }

    private fun expandingLock(
        lock: Array<IntArray>,
        key: Array<IntArray>,
    ): Array<IntArray> {
        val newSize = lock.size * 2 + key.size - 2
        val newLock = Array(newSize) { IntArray(newSize) }

        for (i in lock.indices) {
            for ((j, num) in lock[i].withIndex()) {
                newLock[i + key.lastIndex][j + key.lastIndex] = num
            }
        }
        return newLock
    }
}
