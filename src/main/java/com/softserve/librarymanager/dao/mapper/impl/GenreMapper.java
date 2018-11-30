package com.softserve.librarymanager.dao.mapper.impl;

import com.softserve.librarymanager.dao.mapper.AbstractMapper;
import com.softserve.librarymanager.dao.mapper.EntityMapper;
import com.softserve.librarymanager.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.softserve.librarymanager.dao.table.util.ColumnUtil.prependAliasIfNotEmpty;

public class GenreMapper extends AbstractMapper<Genre> implements EntityMapper<Genre> {
    public GenreMapper() {
    }

    public GenreMapper(String columnAlias) {
        super(columnAlias);
    }

    @Override
    public Genre mapToEntity(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt(prependAliasIfNotEmpty("id", columnAlias)));
        genre.setGenreName(resultSet.getString(prependAliasIfNotEmpty("name", columnAlias)));
        return genre;
    }
}
