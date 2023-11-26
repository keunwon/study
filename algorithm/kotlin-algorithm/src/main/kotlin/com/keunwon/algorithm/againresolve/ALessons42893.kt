package com.keunwon.algorithm.againresolve

class ALessons42893 {
    fun solution(word: String, pages: Array<String>): Int {
        return pages.mapIndexed { index, page -> createHomePage(index, word, page) }
            .apply { calculateLinkScores(this) }
            .sortedWith(compareBy({ -it.matchingScore }, { it.index }))
            .first().index
    }

    private fun calculateLinkScores(pages: List<HomePage>) {
        for (page1 in pages) {
            for (page2 in pages) {
                if (page1.url == page2.url) continue
                page1.addLink(page2)
            }
        }
    }

    private fun createHomePage(index: Int, word: String, page: String): HomePage {
        val url = URL_TAG.findAll(page).first().groupValues[1]
        val body = BODY_TAG.findAll(page).first().groupValues[0]
        val baseScore = run {
            val matchRegex = "(?<![a-z])$word(?![a-z])".toRegex(RegexOption.IGNORE_CASE)
            matchRegex.findAll(body).count()
        }
        val outerLinks = A_TAG.findAll(page).map { it.groupValues[1] }.toList()
        return HomePage(index, url, baseScore, outerLinks)
    }

    private class HomePage(
        val index: Int,
        val url: String,
        val baseScore: Int,
        val outerLinks: List<String>,
    ) {
        var linkScore: Double = 0.0
            private set

        val matchingScore: Double
            get() = baseScore + linkScore

        fun addLink(other: HomePage) {
            if (url in other.outerLinks) {
                linkScore += other.run { baseScore.toDouble() / outerLinks.size }
            }
        }
    }

    companion object {
        private val URL_TAG = """<meta property="og:url" content="https://(\S*)"/>""".toRegex()
        private val BODY_TAG = """<body>[\s\S]*</body>""".toRegex()
        private val A_TAG = """<a href="https://(\S*)">""".toRegex()
    }
}

fun main() {
    ALessons42893().solution(
        "blind",
        arrayOf(
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        )
    ).also(::println)

    ALessons42893().solution(
        "Muzi",
        arrayOf(
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        )
    ).also(::println)
}
