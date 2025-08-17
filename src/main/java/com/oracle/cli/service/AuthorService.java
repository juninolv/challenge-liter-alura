package com.oracle.cli.service;

import com.oracle.cli.dto.AuthorDto;
import com.oracle.cli.repository.AuthorRepository;
import com.oracle.cli.util.mapper.AuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public List<AuthorDto> readAll() {
        return repository
            .findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public List<AuthorDto> readAllLiving(int year) {
        return repository
            .findAliveInYear(year)
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public List<AuthorDto> readAllDead(int year) {
        return repository
            .findDeadByYear(year)
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public AuthorDto readByName(String name) {
        return repository
            .findByNameIgnoreCase(name)
            .map(mapper::toDto)
            .orElse(null);
    }
}
