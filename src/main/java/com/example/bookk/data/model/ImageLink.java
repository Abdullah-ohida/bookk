package com.example.bookk.data.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLink {
    private String smallThumbnail;
    private String thumbnail;
}
