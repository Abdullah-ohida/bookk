package com.example.bookk.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchApiResponse {
    private Integer totalItems;
    private String selfLink;
    private List<SearchResult> items = new ArrayList<>();
}
