package com.github.keunwon.corejpa.p6spy

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import org.hibernate.engine.jdbc.internal.FormatStyle
import java.util.*


class PrettySqlFormattingStrategy : MessageFormattingStrategy {
    override fun formatMessage(
        connectionId: Int,
        now: String?,
        elapsed: Long,
        category: String,
        prepared: String?,
        sql: String?,
        url: String?,
    ): String {
        val query = formatSql(category, sql)
        return now + "|" + elapsed + "ms|" + category + "|connection " + connectionId + "|" + query;
    }

    private fun formatSql(category: String, sql: String?): String? {
        var query = sql
        if (query == null || query.trim { it <= ' ' } == "") return query

        // Only format Statement, distinguish DDL And DML
        if (Category.STATEMENT.name == category) {
            val tmpSql = query.trim { it <= ' ' }.lowercase(Locale.ROOT)
            query = if (tmpSql.startsWith("create") || tmpSql.startsWith("alter") || tmpSql.startsWith("comment")) {
                FormatStyle.DDL.formatter.format(query)
            } else {
                FormatStyle.BASIC.formatter.format(query)
            }
            query = "|\nHeFormatSql(P6Spy sql,Hibernate format):$query"
        }
        return query
    }
}
