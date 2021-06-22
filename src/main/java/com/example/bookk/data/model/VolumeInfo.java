package com.example.bookk.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {
    private String title;
    private String description;
    private ImageLink imageLinks;
    private String previewLink;
    private String publisher;
    private final List<String> authors = new ArrayList<>();
}
