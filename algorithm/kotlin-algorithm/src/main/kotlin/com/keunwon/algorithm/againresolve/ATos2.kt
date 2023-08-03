package com.keunwon.algorithm.againresolve

class ATos2 {
    fun solution(assets: Array<String>): Array<String> {
        return assets.mapNotNull { asset -> runCatching { asset.toAssets() }.getOrNull() }
            .filter(Assets::isValid)
            .sortedWith(compareBy({ it.year }, { it.type.ordinal }, { it.month }, { it.order }))
            .map { it.text }
            .distinct()
            .toTypedArray()
    }

    private fun String.toAssets(): Assets? {
        val values = ASSETS_REGEX.find(this)?.groupValues ?: return null
        return Assets(
            text = this,
            year = values[1].toInt(),
            type = Assets.Type.valueOf(values[2]),
            month = values[3].toInt(),
            order = values[4].toInt(),
        )
    }

    private data class Assets(
        val text: String,
        val year: Int,
        val type: Type,
        val month: Int,
        val order: Int,
    ) {
        enum class Type { SP, KE, MO, CO, DE }

        fun isValid(): Boolean {
            return year in (13..22) &&
                    month in (1..12) &&
                    order in (0..99) &&
                    when (year) {
                        13 -> month in (4..12)
                        in (14..21) -> month in (1..12)
                        22 -> month in (1..8)
                        else -> false
                    }
        }
    }

    companion object {
        private val ASSETS_REGEX = """^([\d]{2})-([A-Z]{2})([\d]{2})([\d]{2})$""".toRegex()
    }
}
