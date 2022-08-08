package chapter03.item45

private fun Iterable<Int>.maxOrNull(): Int? {
    var max: Int? = null
    for (i in this) {
        max = if (i > (max ?: Int.MIN_VALUE)) i else max
    }
    return max
}

private fun Iterable<Int>.maxOrNull2(): Int? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var max = iterator.next()
    while (!iterator.hasNext()) {
        val e = iterator.next()
        if (max < e) max = e
    }
    return max
}
