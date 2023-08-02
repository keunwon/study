package com.keunwon.algorithm.againresolve

class ALessons42893 {
    fun solution(word: String, pages: Array<String>): Int {
        return pages
            .mapIndexed { index, page -> createPagePoint(index, word, page) }
            .apply { calculateLinkPoint(this) }
            .sortedWith(compareBy({ -it.matching }, { it.index }))
            .first().index
    }

    private fun calculateLinkPoint(pagePoints: List<PagePoint>) {
        for (page1 in pagePoints) {
            for (page2 in pagePoints) {
                if (page1.index == page2.index) continue
                page1.addLink(page2)
            }
        }
    }

    private fun createPagePoint(index: Int, word: String, page: String): PagePoint {
        val url = TAG_URL.find(page)!!.groupValues[1]
        val body = TAG_BODY.find(page)!!.groupValues[1]
        val base = run {
            val wordMatch = """(?<![a-z])$word(?![a-z])""".toRegex(RegexOption.IGNORE_CASE)
            wordMatch.findAll(body).count()
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

        fun addLink(other: PagePoint) {
            check(index != other.index)
            if (url in other.outerLinks) {
                link += other.run { base.toDouble() / outerLink }
            }
        }
    }

    companion object {
        private val TAG_URL = """<meta property="og:url" content="https://(\S*)"/>""".toRegex()
        private val TAG_BODY = """<body>([\S\s]*)</body>""".toRegex()
        private val TAG_A = """<a href="https://(\S*)">""".toRegex()
    }
}

fun main() {
    val word = "blind"
    val pages = arrayOf(
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
    )
    ALessons42893().solution(word, pages).also { println(it) }
}
