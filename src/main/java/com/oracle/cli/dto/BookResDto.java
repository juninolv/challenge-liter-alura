package com.oracle.cli.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResDto(
    @JsonAlias("title")
    String title,

    @JsonAlias("download_count")
    Integer downloads,

    @JsonAlias("authors")
    List<AuthorDto> authors,

    @JsonAlias("languages")
    List<String> languages,

    @JsonAlias("subjects")
    List<String> subjects
) { }
