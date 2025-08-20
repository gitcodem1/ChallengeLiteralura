package com.njaimed.literalura.challenge.proxy.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ResponseBooksDTO {
    @JsonProperty("results")
    private List<BookDTO> books;
}