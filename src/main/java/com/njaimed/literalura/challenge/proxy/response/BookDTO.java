package com.njaimed.literalura.challenge.proxy.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BookDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AuthorDTO> authors;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("download_count")
    private int downloadCount;

}