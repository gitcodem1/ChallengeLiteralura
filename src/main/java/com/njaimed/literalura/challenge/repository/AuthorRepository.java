package com.njaimed.literalura.challenge.repository;

import com.njaimed.literalura.challenge.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String nombre);

    @Query("SELECT a " +
            "FROM Author a " +
            "LEFT JOIN FETCH a.books " +
            "WHERE (a.yearOfDeath IS NULL OR a.yearOfDeath > :ano) " +
            "AND a.yearOfBirth <= :ano")
    List<Author> findLivingAuthorsInTheYearWithBooks(@Param("ano") int ano);

    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books")
    List<Author> findAllWithBooks();
}