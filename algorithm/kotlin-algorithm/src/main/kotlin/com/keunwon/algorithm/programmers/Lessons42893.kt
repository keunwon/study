package com.keunwon.algorithm.programmers

/**
 * Title: 매칭 점수
 * Level: 3
 **/
class Lessons42893 {
    fun solution(word: String, pages: Array<String>): Int {
        return pages.mapIndexed { index, page -> createPagePoint(index, word, page) }
            .apply { calculateLinkPoint(this) }
            .sortedWith(compareBy({ -it.matching }, { it.index }))
            .first().index
    }

    private fun calculateLinkPoint(pages: List<PagePoint>) {
        for (page1 in pages) {
            for (page2 in pages) {
                if (page1.url == page2.url) continue
                page1.plusLink(page2)
            }
        }
    }

    private fun createPagePoint(index: Int, word: String, page: String): PagePoint {
        val url = TAG_URL.find(page)!!.groupValues[1]
        val body = TAG_BODY.find(page)!!.groupValues[0]
        val base = run {
            val wordPattern = "(?<![a-z])$word(?![a-z])".toRegex(RegexOption.IGNORE_CASE)
            wordPattern.findAll(body).count()
        }
        val outerLinks = TAG_A.findAll(body).map { it.groupValues[1] }.toList()
        return PagePoint(index, url, base, outerLinks)
    }

    private data class PagePoint(
        val index: Int,
        val url: String,
        val base: Int,
        val outerLinks: List<String>,
    ) {
        val outerLink: Int = outerLinks.size

        var link: Double = 0.0
            private set

        val matching: Double
            get() = base + link

        fun plusLink(other: PagePoint) {
            if (url in other.outerLinks) {
                link += other.run { base.toDouble() / outerLink }
            }
        }
    }

    companion object {
        private val TAG_URL = """<meta property="og:url" content="https://(\S*)"/>""".toRegex()
        private val TAG_BODY = """<body>[\s\S]*</body>""".toRegex()
        private val TAG_A = """<a href="https://(\S*)">""".toRegex()
    }
}
