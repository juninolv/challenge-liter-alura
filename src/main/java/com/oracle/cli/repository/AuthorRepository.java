package com.oracle.cli.repository;

import com.oracle.cli.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findAliveInYear(int year);

    @Query("SELECT a FROM Author a WHERE a.deathYear <= :year AND a.deathYear IS NOT NULL")
    List<Author> findDeadByYear(int year);

    Optional<Author> findByNameIgnoreCase(String name);
}
