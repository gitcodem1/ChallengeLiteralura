package com.njaimed.literalura.challenge.repository.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String language;
    private int numberDownloads;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public String toString() {
        return "-----*** LIBRO ***-----\n" +
                "*Título: " + title + "\n" +
                "*Autor: " + (author != null ? author.getName() : "Desconocido (N/A)") + "\n" +
                "*language: " + language + "\n" +
                "*Número de descargas: " + numberDownloads + "\n" +
                "------------------------------";
    }
}