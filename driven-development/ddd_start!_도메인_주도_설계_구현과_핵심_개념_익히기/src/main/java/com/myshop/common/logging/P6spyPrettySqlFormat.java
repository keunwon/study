package com.myshop.common.logging;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class P6spyPrettySqlFormat implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        final String prettySql = formatSql(category, sql);
        return now + "|" + elapsed + "ms|" + category + "|connection " + connectionId + "|" + P6Util.singleLine(prepared) + prettySql;
        //return now + "|" + elapsed + "ms|" + category + "|connection " + connectionId + "|" + prettySql;
    }

    private String formatSql(String category, String sql) {
        if (!isSupport(category, sql)) { return sql; }

        String tempSql = sql.trim().toLowerCase(Locale.ROOT);
        if(tempSql.startsWith("create") || tempSql.startsWith("alter") || tempSql.startsWith("comment")) {
            sql = FormatStyle.DDL.getFormatter().format(sql);
        }else {
            sql = FormatStyle.BASIC.getFormatter().format(sql);
        }
        sql = "|\nHeFormatSql(P6Spy sql,Hibernate format):"+ sql;
        return sql;
    }

    private boolean isSupport(String category, String sql) {
        return StringUtils.hasText(sql) && Category.STATEMENT.getName().equals(category);
    }
}
