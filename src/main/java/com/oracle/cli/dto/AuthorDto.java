package com.oracle.cli.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDto(
    @JsonAlias("name")
    String name,

    @JsonAlias("birth_year")
    Short birthYear,

    @JsonAlias("death_year")
    Short deathYear
) {
    @Override
    @NonNull
    public String toString() {
        return name;
    }
}
