package com.oracle.cli.util.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.oracle.cli.dto.BookDto;
import com.oracle.cli.dto.BookResDto;
import com.oracle.cli.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
@AllArgsConstructor
public class BookMapper {
    private final ObjectMapper mapper;

    public <T> BookResDto toResDto(T obj) {
        return mapper.convertValue(obj, BookResDto.class);
    }

    public <T> BookDto toDto(T obj) {
        return mapper.convertValue(obj, BookDto.class);
    }

    public <T> Book toEntity(T obj) {
        if (obj instanceof BookResDto) {
            throw new InvalidParameterException();
        }

        return mapper.convertValue(obj, Book.class);
    }

    public ArrayNode toArrayNode(String json, String property) throws JsonProcessingException {
        return mapper
            .readTree(json)
            .withArrayProperty(property);
    }
}
