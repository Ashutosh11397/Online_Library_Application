package com.example.Online_Library_Application.controller;

import com.example.Online_Library_Application.DTO.BookDTO.AddBookRequest;
import com.example.Online_Library_Application.DTO.BookDTO.AddBookResponse;
import com.example.Online_Library_Application.DTO.BookDTO.UpdateBookStatusRequest;
import com.example.Online_Library_Application.model.Book;
import com.example.Online_Library_Application.model.BookStatus;
import com.example.Online_Library_Application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    //Add By books
    @PostMapping("/addByBooks")
    public ResponseEntity<?> addBooks(@RequestBody List<AddBookRequest> requests) {
        List<Book> savedBooks = new ArrayList<>();
        List<String> skippedBooks = new ArrayList<>();

        for (AddBookRequest request : requests) {
            if (bookService.existsByName(request.getBookName())) {
                skippedBooks.add(request.getBookName());
                continue;
            }

            Book book = new Book();
            book.setBookName(request.getBookName());
            book.setAuthor(request.getAuthor());
            book.setCategory(request.getCategory());
            book.setStatus(BookStatus.AVAILABLE);
            savedBooks.add(bookService.addBook(book));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("savedBooks", savedBooks);
        response.put("skippedDuplicates", skippedBooks);

        return ResponseEntity.ok(response);
    }

    //get By all booklist
    @GetMapping("/GetBYAllBooks")
    public ResponseEntity<List<AddBookResponse>> getAllBookList() {
        return ResponseEntity.ok(bookService.getAllBookList());
    }

    //update book status
    @PutMapping("ChangeBook/status")
    public ResponseEntity<?> updateBookStatus(@RequestBody UpdateBookStatusRequest request) {
        return ResponseEntity.ok(bookService.updateBookStatusByName(request.getBookName(), request.getStatus()));
    }
    
}
