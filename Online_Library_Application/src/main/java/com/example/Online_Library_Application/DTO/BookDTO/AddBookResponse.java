package com.example.Online_Library_Application.DTO.BookDTO;

import com.example.Online_Library_Application.model.BookStatus;

import java.time.LocalDate;

public class AddBookResponse {
    private  Long id;
    private String bookName;
    private String author;
    private String category;
    private BookStatus status;
    private LocalDate createdAt;

    public AddBookResponse(Long id, String bookName, String author, String category, BookStatus status, LocalDate createdAt) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
         bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


}
