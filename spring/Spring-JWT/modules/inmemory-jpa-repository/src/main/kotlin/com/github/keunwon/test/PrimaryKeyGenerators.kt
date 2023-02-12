package com.github.keunwon.test

interface PrimaryKeyGenerator<ID> {
    fun nextPrimaryKey(preKey: ID): ID
}

class LongPrimaryKeyGenerator : PrimaryKeyGenerator<Long> {
    override fun nextPrimaryKey(preKey: Long): Long {
        return if (preKey == 0L) 1L else preKey + 1
    }
}

class IntPrimaryKeyGenerator : PrimaryKeyGenerator<Int> {
    override fun nextPrimaryKey(preKey: Int): Int {
        return if (preKey == 0) 1 else preKey + 1
    }
}

class NumberStringPrimaryKeyGenerator : PrimaryKeyGenerator<String> {
    override fun nextPrimaryKey(preKey: String): String {
        if (preKey.isBlank()) return "1"
        val value = preKey.toLong()
        return if (value == 0L) "1" else value.plus(1).toString()
    }
}
