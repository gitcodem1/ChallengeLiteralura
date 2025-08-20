package com.njaimed.literalura.challenge.proxy;

import com.njaimed.literalura.challenge.proxy.response.AuthorDTO;
import com.njaimed.literalura.challenge.proxy.response.BookDTO;
import com.njaimed.literalura.challenge.proxy.response.ResponseBooksDTO;
import com.njaimed.literalura.challenge.repository.entity.Author;
import com.njaimed.literalura.challenge.repository.entity.Book;
import com.njaimed.literalura.challenge.service.AuthorService;
import com.njaimed.literalura.challenge.service.BookService;
import com.njaimed.literalura.challenge.utils.IConvertData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GutendexAPI {

    private final IConvertData convertData;
    private final BookService bookService;
    private final AuthorService authorService;

    private static final String BASE_URL = "https://gutendex.com/books/";

    public void searchBooks(String title) {
        try {
            String json = HttpClient
                    .retrieveData(BASE_URL + "?search=" + URLEncoder.encode(title, StandardCharsets.UTF_8));

            ResponseBooksDTO response = convertData.retrieveData(json, ResponseBooksDTO.class);

            if (response.getBooks().isEmpty()) {
                System.out.println("Libro no encontrado en la API.");
            }

            manageFoundBooks(response.getBooks(), title);
        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
        }
    }

    private void manageFoundBooks(List<BookDTO> libros, String tituloBuscado) {
        boolean libroRegistrado = false;

        for (BookDTO book : libros) {
            if (book.getTitle().equalsIgnoreCase(tituloBuscado)) {
                Optional<Book> bookExists = bookService.getBookByTitle(tituloBuscado);

                if (bookExists.isPresent()) {
                    System.out.println("El libro ya está registrado: " + tituloBuscado);
                } else {
                    registrarLibro(book);
                }
                libroRegistrado = true;
                break;
            }
        }

        if (!libroRegistrado) {
            System.out.println("No se encontró un libro exactamente con el título '" + tituloBuscado + "'.");
        }
    }

    private void registrarLibro(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setLanguage(bookDTO.getLanguages().get(0));
        book.setNumberDownloads(bookDTO.getDownloadCount());

        AuthorDTO authorDTO = bookDTO.getAuthors().get(0);
        Author author = authorService.getAuthorByName(authorDTO.getName())
                .orElseGet(() -> authorService.saveAuthor(Author.builder()
                        .name(authorDTO.getName())
                        .yearOfBirth(authorDTO.getBirthYear())
                        .yearOfDeath(authorDTO.getDeathYear())
                        .build()));

        book.setAuthor(author);
        bookService.saveBook(book);

        System.out.println("Libro registrado: " + book.getTitle());
        showBookDetails(bookDTO);
    }

    private void showBookDetails(BookDTO bookDTO) {
        System.out.println("------LIBRO--------");
        System.out.println("Título: " + bookDTO.getTitle());
        System.out.println("Autor: " + (bookDTO.getAuthors().isEmpty() ? "Desconocido"
                : bookDTO.getAuthors().get(0).getName()));
        System.out.println("Idioma: " + bookDTO.getLanguages().get(0));
        System.out.println("Número de descargas: " + bookDTO.getDownloadCount());
    }
}
