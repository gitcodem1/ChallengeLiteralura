package com.njaimed.literalura.challenge.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int yearOfBirth;

    private Integer yearOfDeath;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    @Override
    public String toString() {
        return  "*Autor: " + name + "\n" +
                "*Fecha de Nacimiento: " + yearOfBirth + "\n" +
                "*Fecha de Fallecimiento: " + (yearOfDeath != null ? yearOfDeath : "Desconocido(N/A)") + "\n" +
                "*Libros: " + (books != null ? books.size() : "Ningún libro registrado");
    }
}