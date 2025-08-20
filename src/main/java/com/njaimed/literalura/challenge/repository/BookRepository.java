package com.njaimed.literalura.challenge.repository;


import com.njaimed.literalura.challenge.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);

    @Query("SELECT l FROM Book l WHERE l.language = :language")
    List<Book> findByLanguage(@Param("language") String language);
}