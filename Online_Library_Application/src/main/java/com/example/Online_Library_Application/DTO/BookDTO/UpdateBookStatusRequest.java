package com.example.Online_Library_Application.DTO.BookDTO;

import com.example.Online_Library_Application.model.BookStatus;

public class UpdateBookStatusRequest {
    private String bookName;
    private BookStatus status;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        bookName = bookName;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
