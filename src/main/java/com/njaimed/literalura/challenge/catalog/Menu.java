package com.njaimed.literalura.challenge.catalog;

import com.njaimed.literalura.challenge.proxy.GutendexAPI;
import com.njaimed.literalura.challenge.repository.entity.Author;
import com.njaimed.literalura.challenge.service.AuthorService;
import com.njaimed.literalura.challenge.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component
@RequiredArgsConstructor
public class Menu {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GutendexAPI gutendexAPI;

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int opcion;

        do {
            printMenu();
            opcion = readOption();

            switch (opcion) {
                case 1 -> searchBookByTitle();
                case 2 -> listRegisteredBooks();
                case 3 -> listRegisteredAuthors();
                case 4 -> listLivingAuthorsByYear();
                case 5 -> listBooksByLanguage();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private void printMenu() {
        System.out.println("--- LITERALURA ---");
        System.out.println("1 - Buscar libro por título");
        System.out.println("2 - Listar libros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos en un año");
        System.out.println("5 - Listar libros por idioma");
        System.out.println("0 - Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int readOption() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");

        }
        return scanner.nextInt();
    }

    private void searchBookByTitle() {
        System.out.print("Ingrese el título del libro: ");
        scanner.nextLine();

        gutendexAPI.searchBooks(scanner.nextLine());
    }

    private void listRegisteredBooks() {
        bookService.getAllBooks().forEach(book -> {
            System.out.println("------LIBRO--------");
            System.out.println("Título: " + book.getTitle());
            System.out.println("Autor: " + (book.getAuthor() != null ? book.getAuthor().getName() : "Desconocido"));
            System.out.println("Idioma: " + book.getLanguage());
            System.out.println("Número de descargas: " + book.getNumberDownloads());
        });
    }

    private void listRegisteredAuthors() {
        authorService.getAllAuthors().forEach(author -> {
            System.out.println("-------AUTOR-------");
            System.out.println("Nombre: " + author.getName());
            System.out.println("Fecha de nacimiento: " + author.getYearOfBirth());
            System.out.println("Fecha de fallecimiento: " +
                    (author.getYearOfDeath() != null ? author.getYearOfDeath() : "Desconocido"));
        });
    }

    private void listLivingAuthorsByYear() {
        System.out.print("Ingrese el año: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        List<Author> livingAuthors = authorService.listLivingAuthorsInTheYear(ano);
        if (livingAuthors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + ano);
        } else {
            livingAuthors.forEach(autor -> System.out.println("Autor vivo: " + autor.getName()));
        }
    }

    private void listBooksByLanguage() {
        System.out.print("Ingrese el idioma (es, en, fr, pt): ");
        String language = scanner.next();
        System.out.println("------LIBRO--------");

        bookService.listBooksByLanguage(language).forEach(libro -> {
            System.out.println("Título: " + libro.getTitle());
            System.out.println("Idioma: " + libro.getLanguage());
        });

        scanner.nextLine();
    }
}
