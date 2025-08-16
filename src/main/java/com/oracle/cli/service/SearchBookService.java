package com.oracle.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.oracle.cli.dto.BookResDto;
import com.oracle.cli.util.Http;
import com.oracle.cli.util.exception.ExitException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SearchBookService {
    private final ObjectMapper mapper;

    @Value("${api.base.url}")
    private String url;

    public SearchBookService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public BookResDto search(String title) {
        ArrayNode data = this.sendRequest(this.buildUri(title));

        return this.processRequest(data, title);
    }

    @NonNull
    private String buildUri(@NonNull String value) {
        return String.format(url, value.replace(" ", "%20"));
    }

    @NonNull
    private ArrayNode sendRequest(String uri) {
        ArrayNode result = null;

        try {
            result = mapper.readTree(Http.get(uri)).withArrayProperty("results");
        } catch (ExecutionException | InterruptedException | JsonProcessingException exception) {
            System.out.println("\n# Server offline or invalid resource. Leaving...");
            Thread.currentThread().interrupt();
        }

        if (result == null) {
            throw new ExitException("Invalid Request");
        }

        return result;
    }

    private BookResDto processRequest(@NonNull ArrayNode values, String title) {
        return values.valueStream()
            .filter(entity -> entity.get("title").asText().equalsIgnoreCase(title))
            .map(entity -> mapper.convertValue(entity, BookResDto.class))
            .findFirst()
            .orElse(null);
    }
}
