package com.oracle.cli.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorResDto(
    @JsonAlias("name")
    String name,

    @JsonAlias("birth_year")
    Short birthYear,

    @JsonAlias("death_year")
    Short deathYear
) { }
