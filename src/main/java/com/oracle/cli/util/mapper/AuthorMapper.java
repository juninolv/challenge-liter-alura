package com.oracle.cli.util.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.cli.dto.AuthorDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorMapper {
    private final ObjectMapper mapper;

    public <T> AuthorDto toDto(T obj) {
        return mapper.convertValue(obj, AuthorDto.class);
    }
}
