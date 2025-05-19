package com.example.Online_Library_Application.controller;

import com.example.Online_Library_Application.DTO.BookDTO.UserBooksResponseDTO;
import com.example.Online_Library_Application.DTO.BorrowedDTO.BorrowRequest;
import com.example.Online_Library_Application.DTO.BorrowedDTO.CategoryReportDTO;
import com.example.Online_Library_Application.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowedBookController {
    @Autowired
    private BorrowedBookService borrowedBookService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest request) {
        String message = borrowedBookService.borrowBook(request.getUserId(), request.getBookId());
        return ResponseEntity.ok(message);
    }

    @PutMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowRequest request) {
        try {
            String message = borrowedBookService.returnBook(request.getUserId(), request.getBookId());
            return ResponseEntity.ok(message);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<UserBooksResponseDTO> getUserBooks(@PathVariable Long userId) {
        UserBooksResponseDTO response = borrowedBookService.getUserBooks(userId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category-report")
    public ResponseEntity<List<CategoryReportDTO>> getCategoryReport() {
        List<CategoryReportDTO> report = borrowedBookService.getCategoryReport();
        return ResponseEntity.ok(report);
    }
}
