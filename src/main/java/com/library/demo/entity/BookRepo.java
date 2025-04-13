package com.library.demo.entity;

import org.jdbi.v3.spring.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@JdbiRepository
@RegisterBeanMapper(Book.class)
public interface BookRepo {
    String SELECT_BOOK_QUERY = "SELECT id, title, author, publisher, isbn, published_date FROM book";

    @SqlQuery(SELECT_BOOK_QUERY + " WHERE id = :id")
    Optional<Book> getOneById(@Bind("id") BookId id);

    @SqlQuery(SELECT_BOOK_QUERY)
    List<Book> getAll();

    @SqlUpdate("DELETE FROM book WHERE id = :id")
    void deleteById(@Bind("id") BookId id);
}
