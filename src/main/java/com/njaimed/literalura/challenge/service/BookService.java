package com.njaimed.literalura.challenge.service;

import com.njaimed.literalura.challenge.repository.BookRepository;
import com.njaimed.literalura.challenge.repository.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listBooksByLanguage(String language) {
        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("Libro no encontrado con ese idioma: ' " + language + " ' ");
        }
        return books;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public Book updateBook(Long id, Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setLanguage(newBook.getLanguage());
                    book.setNumberDownloads(newBook.getNumberDownloads());
                    book.setAuthor(newBook.getAuthor());
                    return book;
                })
                .map(bookRepository::save)
                .orElseThrow(() -> new RuntimeException("*Libro no encontrado*"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}