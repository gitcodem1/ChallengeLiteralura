package com.njaimed.literalura.challenge.service;

import com.njaimed.literalura.challenge.repository.AuthorRepository;
import com.njaimed.literalura.challenge.repository.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAllWithBooks();
    }

    public List<Author> listLivingAuthorsInTheYear(int ano) {
        return authorRepository.findLivingAuthorsInTheYearWithBooks(ano);
    }

    public Author saveAuthor(Author autor) {
        return authorRepository.save(autor);
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> getAuthorByName(String nombre) {
        return authorRepository.findByName(nombre);
    }

    public Author updateAuthor(Long id, Author autorDetalles) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        author.setName(autorDetalles.getName());
        author.setYearOfBirth(autorDetalles.getYearOfBirth());
        author.setYearOfDeath(autorDetalles.getYearOfDeath());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}