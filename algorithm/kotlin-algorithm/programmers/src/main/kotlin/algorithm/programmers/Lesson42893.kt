package algorithm.programmers

class Lesson42893 {
    fun solution(word: String, pages: Array<String>): Int {
        return pages.mapIndexed { index, page -> createWebPage(index, word, page) }
            .calculateLinkPoint()
            .maxWithOrNull(compareBy({ it.matchingPoint }, { -it.index }))
            ?.index ?: -1
    }

    private fun createWebPage(index: Int, word: String, page: String): WebPage {
        val url = URL_TAG.find(page)!!.groupValues[1]
        val body = BODY_TAG.find(page)!!.groupValues[0]
        val basePoint = run {
            val wordRegex = "(?<![a-z])$word(?![a-z])".toRegex(RegexOption.IGNORE_CASE)
            wordRegex.findAll(body).count()
        }
        val outerLinks = A_TAG.findAll(body).map { it.groupValues[1] }.toList()

        return WebPage(
            index = index,
            url = url,
            basePoint = basePoint,
            outerLinks = outerLinks,
        )
    }

    private fun Iterable<WebPage>.calculateLinkPoint(): Iterable<WebPage> {
        for (p1 in this) {
            for (p2 in this) {
                if (p1.index != p2.index && p1.url in p2.outerLinks) {
                    p1.linkPoint += p2.basePoint.toDouble() / p2.outerLinks.size
                }
            }
        }
        return this
    }

    private class WebPage(
        val index: Int,
        val url: String,
        val basePoint: Int,
        val outerLinks: List<String>,
    ) {
        var linkPoint: Double = 0.0

        val matchingPoint: Double
            get() = basePoint + linkPoint
    }

    companion object {
        private val URL_TAG = """<meta property="og:url" content="https://(\S*)"""".toRegex()
        private val BODY_TAG = """<body>[\S\s]*</body>""".toRegex()
        private val A_TAG = """<a href="https://(\S*)"""".toRegex()
    }
}
