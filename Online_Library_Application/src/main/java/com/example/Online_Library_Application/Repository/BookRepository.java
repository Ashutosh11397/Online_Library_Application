package com.example.Online_Library_Application.Repository;

import com.example.Online_Library_Application.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByBookNameIgnoreCase(String bookName);

    Optional<Book> findByBookName(String bookName);
}
