package com.softserve.librarymanager.dao.table.util;

public final class ColumnUtil {
    private static final String ALIAS_FORMAT = "%s.%s";

    private ColumnUtil() {
    }

    public static String addAliasIfProvided(String column, String alias) {
        if (alias.isEmpty())
            return column;
        return String.format(ALIAS_FORMAT, alias, column);
    }
}
