package com.oracle.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.oracle.cli.dto.BookResDto;
import com.oracle.cli.util.Http;
import com.oracle.cli.util.exception.ExitException;
import com.oracle.cli.util.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SearchBookService {
    private final BookMapper mapper;

    @Value("${api.base.url}")
    private String url;

    public SearchBookService(BookMapper mapper) {
        this.mapper = mapper;
    }

    public BookResDto search(String title) throws ExecutionException, InterruptedException, JsonProcessingException {
        ArrayNode data = this.sendRequest(this.buildUri(title));

        return this.processRequest(data, title);
    }

    @NonNull
    private String buildUri(@NonNull String value) {
        return String.format(url, value.replace(" ", "%20"));
    }

    @NonNull
    private ArrayNode sendRequest(String uri) throws ExecutionException, InterruptedException, JsonProcessingException {
        ArrayNode result = mapper.toArrayNode(Http.get(uri), "results");

        if (result == null) {
            throw new ExitException("Invalid Request");
        }

        return result;
    }

    private BookResDto processRequest(@NonNull ArrayNode values, String title) {
        return values.valueStream()
            .filter(entity -> this.isEquals(entity, title))
            .map(mapper::toResDto)
            .findFirst()
            .orElse(null);
    }

    private boolean isEquals(@NonNull JsonNode obj, String title) {
        return obj.get("title").asText().equalsIgnoreCase(title);
    }
}
