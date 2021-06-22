package com.example.bookk.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String id;
    private String title;
    private String publisher;
    private String image;
    private String smallImage;
    private String description;
    private String previewLink;
    private List<String> authors = new ArrayList<>();
}
