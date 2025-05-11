package com.example.Online_Library_Application.DTO.BookDTO;

import java.util.List;

public class UserBooksResponseDTO {
    private Long userId;
    private String userName;
    private List<String> previouslyReadBooks;
    private String currentlyBorrowedBooks;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getPreviouslyReadBooks() {
        return previouslyReadBooks;
    }

    public void setPreviouslyReadBooks(List<String> previouslyReadBooks) {
        this.previouslyReadBooks = previouslyReadBooks;
    }

    public String getCurrentlyBorrowedBooks() {
        return currentlyBorrowedBooks;
    }

    public void setCurrentlyBorrowedBooks(String currentlyBorrowedBooks) {
        this.currentlyBorrowedBooks = currentlyBorrowedBooks;
    }
}
