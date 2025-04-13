package com.library.demo.entity;

import org.jdbi.v3.spring.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@JdbiRepository
@RegisterBeanMapper(Library.class)
public interface LibraryRepo {
    String SELECT_LIBRARY_QUERY = "SELECT id, name, author, description FROM library";

    @SqlQuery(SELECT_LIBRARY_QUERY + " WHERE id = :id")
    Optional<Book> getOneById(LibraryId id);

    @SqlQuery(SELECT_LIBRARY_QUERY)
    List<Book> getAll();

    @SqlUpdate("INSERT INTO book_in_library (library_id, book_id) VALUES (:libraryId, :bookId)")
    void addBookToLibrary(
            @Bind("libraryId") LibraryId libraryId,
            @Bind("bookId") BookId bookId
    );

    @SqlUpdate("INSERT INTO library (name, description) VALUES (:name, :description)")
    @GetGeneratedKeys("id")
    BookId save(@BindMethods LibraryCreateRequest book);
}
