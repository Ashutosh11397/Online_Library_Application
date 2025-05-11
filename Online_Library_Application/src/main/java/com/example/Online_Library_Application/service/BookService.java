package com.example.Online_Library_Application.service;

import com.example.Online_Library_Application.DTO.BookDTO.AddBookResponse;
import com.example.Online_Library_Application.DTO.UserDTO.UserResponseDTO;
import com.example.Online_Library_Application.Repository.BookRepository;
import com.example.Online_Library_Application.model.Book;
import com.example.Online_Library_Application.model.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean existsByName(String bookName) {
        return bookRepository.existsByBookNameIgnoreCase(bookName);
    }

    public List<AddBookResponse> getAllBookList() {
        return bookRepository.findAll().stream()
                .map(book -> new AddBookResponse(
                        book.getId(),
                        book.getBookName(),
                        book.getAuthor(),
                        book.getCategory(),
                        book.getStatus(),
                        book.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public String updateBookStatusByName(String BookName, BookStatus status) {
        Book book = bookRepository.findByBookName(BookName)
                .orElseThrow(() -> new RuntimeException("Book not found with name: " + BookName));

        book.setStatus(status);
        bookRepository.save(book);

        return "Book status updated successfully.";
    }


}
