package com.softserve.bookworm.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<E> {
    E mapToEntity(ResultSet resultSet) throws SQLException;
}
