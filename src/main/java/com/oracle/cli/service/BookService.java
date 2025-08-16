package com.oracle.cli.service;

import com.oracle.cli.dto.BookDto;
import com.oracle.cli.dto.BookResDto;
import com.oracle.cli.model.Book;
import com.oracle.cli.repository.BookRepository;
import com.oracle.cli.util.mapper.BookMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    @Transactional
    public BookDto create(BookResDto data) {
        BookDto dto = new BookDto(data);
        Book entity = repository.saveAndFlush(mapper.toEntity(dto));

        return mapper.toDto(entity);
    }

    public List<BookDto> readAll() {
        return repository
            .findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public List<BookDto> readMostDownload() {
        return repository
            .findAll()
            .stream()
            .sorted(Comparator.comparing(Book::getDownloads).reversed())
            .limit(10)
            .map(mapper::toDto)
            .toList();
    }

    public BookDto readIfExistsByTitle(String title) {
        return repository
            .findByTitle(title)
            .map(mapper::toDto)
            .orElse(null);
    }
}
