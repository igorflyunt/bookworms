package com.softserve.bookworm.dao.impl;

import com.softserve.bookworm.dao.AbstractDao;
import com.softserve.bookworm.dao.BookDao;
import com.softserve.bookworm.dao.Dao;
import com.softserve.bookworm.dao.mapper.impl.BookMapper;
import com.softserve.bookworm.dao.table.TableAliases;
import com.softserve.bookworm.dao.table.TablePrimaryKeyPair;
import com.softserve.bookworm.db.JDBCQuery;
import com.softserve.bookworm.model.Book;

import java.util.List;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao, Dao<Book> {
    private static final String SQL_INSERT_BOOK = "insert into book (name, description, first_published)" +
            " values (?, ?, ?)";
    private static final String SQL_INSERT_BOOK_INTO_AUTHOR = "insert into author_book (author_id, book_id)" +
            " values (?, ?)";
    private static final String SQL_UPDATE_BOOK = "update book" +
            " set name = ?, description = ?," +
            " first_published = ?" +
            " where id = ?";
    private static final String SQL_SELECT_BOOKS_BY_AUTHOR_ID = String.format(
            "select %s.* from book %s" +
            " inner join author_book %s" +
            " on %s.id = %s.book_id" +
            " where %s.author_id = ?", TableAliases.BOOK, TableAliases.BOOK, TableAliases.AUTHOR_BOOK,
            TableAliases.BOOK, TableAliases.AUTHOR_BOOK, TableAliases.AUTHOR_BOOK);
    private static final String SQL_SELECT_TEN_LATEST_BOOKS = "select * from book order by first_published desc limit 2";

    public BookDaoImpl() {
        this(TablePrimaryKeyPair.BOOK, new BookMapper());
    }

    private BookDaoImpl(TablePrimaryKeyPair tablePrimaryKeyPair, BookMapper entityMapper) {
        super(tablePrimaryKeyPair, entityMapper);
    }

    @Override
    public List<Book> findAllBooksByAuthorId(int authorId) {
        return JDBCQuery.selectMany(SQL_SELECT_BOOKS_BY_AUTHOR_ID, new BookMapper(TableAliases.BOOK), authorId);
    }

    @Override
    public List<Book> findTenLatestBooks() {
        return JDBCQuery.selectMany(SQL_SELECT_TEN_LATEST_BOOKS, new BookMapper());
    }

    @Override
    public void mapBookToAuthor(int bookId, int authorId) {
        JDBCQuery.update(null, SQL_INSERT_BOOK_INTO_AUTHOR, authorId, bookId);
    }

    @Override
    public Book saveOrUpdate(Book entity) {
        int bookId = entity.getId();
        if (entityExists(bookId)) {
            JDBCQuery.update(entity, SQL_UPDATE_BOOK, entity.getName(), entity.getDescription(),
                    entity.getFirstPublished(), bookId);
        } else {
            JDBCQuery.update(entity, SQL_INSERT_BOOK, entity.getName(), entity.getDescription(),
                    entity.getFirstPublished());
        }
        return entity;
    }
}
