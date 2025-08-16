package com.oracle.cli.repository;

import com.oracle.cli.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByTitle(String title);
    List<Book> findAllByLanguagesContains(String language);
}
