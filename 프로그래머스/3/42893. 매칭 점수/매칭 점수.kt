class Solution {
    fun solution(word: String, pages: Array<String>): Int {
        val webPages = pages.mapIndexed { index, page -> createWebPage(index, page, word) }
        for (p1 in webPages) {
            for (p2 in webPages) {
                if (p1.idx != p2.idx) {
                    p1.calculateLinkPoint(p2)
                }
            }
        }
        return webPages.maxWith(compareBy({ it.matchPoint }, { -it.idx })).idx
    }

    private fun createWebPage(idx: Int, page: String, word: String): WebPage {
        val url = URL_TAG.find(page)!!.groupValues[1]
        val basePoint = run {
            val wordRegex = """(?<![a-z])$word(?![a-z])""".toRegex(RegexOption.IGNORE_CASE)
            wordRegex.findAll(page).count()
        }
        val outerLinks = A_TAG.findAll(page).map { it.groupValues[1] }.toList()

        return WebPage(
            idx = idx,
            url = url,
            basePoint = basePoint,
            outerLinks = outerLinks,
        )
    }

    private class WebPage(
        val idx: Int,
        val url: String,
        val basePoint: Int,
        var outerLinks: List<String>,
    ) {
        var linkPoint = 0.0
            private set

        val matchPoint: Double get() = basePoint + linkPoint

        fun calculateLinkPoint(other: WebPage) {
            if (url in other.outerLinks) {
                linkPoint += other.basePoint.toDouble() / other.outerLinks.size
            }
        }
    }

    companion object {
        private val URL_TAG = """<meta property="og:url" content="https://(\S+)"""".toRegex()
        private val A_TAG = """<a href="https://(\S+)"""".toRegex()
    }
}