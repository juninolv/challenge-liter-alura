package com.oracle.cli.repository;

import com.oracle.cli.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b WHERE UPPER(b.languages) LIKE UPPER(CONCAT('%', :language, '%'))")
    List<Book> findAllByLanguages(String language);

    @Query("SELECT b FROM Book b WHERE UPPER(b.subjects) LIKE UPPER(CONCAT('%', :subject, '%'))")
    List<Book> findAllBySubjects(String subject);

    Optional<Book> findByTitle(String title);
}
