package com.njaimed.literalura.challenge.proxy.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AuthorDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private int birthYear;

    @JsonProperty("death_year")
    private int deathYear;
}