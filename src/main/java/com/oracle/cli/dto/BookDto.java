package com.oracle.cli.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto(
    @JsonAlias("title") String title,
    @JsonAlias("download_count") Integer downloads,
    @JsonAlias("authors") List<AuthorDto> authors,
    @JsonAlias("languages") String languages,
    @JsonAlias("subjects") String subjects
) {
    public BookDto(@NonNull BookResDto data) {
        this(
            data.title(),
            data.downloads(),
            data.authors(),
            join(data.languages()),
            join(data.subjects())
        );
    }

    @NonNull
    public static String join(List<String> values) {
        return String.join(", ", values);
    }

    @NonNull
    public String joinAuthor() {
        return authors
            .stream()
            .map(AuthorDto::name)
            .collect(Collectors.joining(" | "));
    }
}
