package com.oracle.cli.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tittle")
    private String title;

    @Column(name = "downloads_count")
    private Integer downloads;

    @ManyToMany(
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_fk"),
        inverseJoinColumns = @JoinColumn(name = "author_fk")
    )
    private List<Author> authors;

    @Column(name = "languages")
    private String languages;

    @Column(name = "subjects", columnDefinition = "TEXT")
    private String subjects;
}
