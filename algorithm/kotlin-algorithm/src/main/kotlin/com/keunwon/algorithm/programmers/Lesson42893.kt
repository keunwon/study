package com.keunwon.algorithm.programmers

class Lesson42893 {
    fun solution(word: String, pages: Array<String>): Int {
        return pages.mapIndexed { index, page -> generateWebPage(index, word, page) }
            .apply { calculateLinkPoints(this) }
            .sortedWith(compareBy({ -it.matchingPoint }, { it.index }))
            .first().index
    }

    private fun calculateLinkPoints(pages: List<WebPage>) {
        for (page1 in pages) {
            for (page2 in pages) {
                if (page1.index != page2.index) {
                    page1.plusLinkPoint(page2)
                }
            }
        }
    }

    private fun generateWebPage(index: Int, word: String, page: String): WebPage {
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

    private class WebPage(
        val index: Int,
        val url: String,
        val basePoint: Int,
        val outerLinks: List<String>,
    ) {
        var linkPoint: Double = 0.0
            private set

        val matchingPoint: Double
            get() = basePoint + linkPoint

        fun plusLinkPoint(other: WebPage) {
            if (url in other.outerLinks) {
                linkPoint += other.run { basePoint.toDouble() / outerLinks.size }
            }
        }
    }

    companion object {
        private val URL_TAG = """<meta property="og:url" content="https://(\S*)"""".toRegex()
        private val BODY_TAG = """<body>[\S\s]*</body>""".toRegex()
        private val A_TAG = """<a href="https://(\S*)"""".toRegex()
    }
}
